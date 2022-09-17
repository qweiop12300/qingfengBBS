package com.chenbaolu.qflt.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.Target;
import com.chenbaolu.baselib.base.BaseActivity;
import com.chenbaolu.baselib.network.bean.dto.PostDto;
import com.chenbaolu.baselib.network.bean.pojo.ImageStatus;
import com.chenbaolu.baselib.network.bean.pojo.QiNiuToken;
import com.chenbaolu.qflt.Adapter.ImageRecyclerViewAdapter;
import com.chenbaolu.qflt.CallBack.ListCallBack;
import com.chenbaolu.qflt.MVP.Presenter.AddPostPresenter;
import com.chenbaolu.qflt.MyApplication;
import com.chenbaolu.qflt.R;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.utils.ContextGetter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.noties.markwon.Markwon;
import io.noties.markwon.core.spans.StrongEmphasisSpan;
import io.noties.markwon.editor.AbstractEditHandler;
import io.noties.markwon.editor.MarkwonEditor;
import io.noties.markwon.editor.MarkwonEditorTextWatcher;
import io.noties.markwon.editor.PersistedSpans;
import io.noties.markwon.image.AsyncDrawable;
import io.noties.markwon.image.glide.GlideImagesPlugin;

@AndroidEntryPoint
public class AddPostActivity extends BaseActivity implements AddPostPresenter.View {

    @Inject
    AddPostPresenter.Model model;

    ImageRecyclerViewAdapter imageRecyclerViewAdapter;

    private List<ImageStatus> imageStatuses = new ArrayList<>();

    private QiNiuToken qiNiuToken;

    UploadManager uploadManager = new UploadManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        model.setModel(this);
        request();
    }

    void request(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},11);
        }else{
            init();
        }
    }

    void init(){
        MarkwonEditor editor = MarkwonEditor.create(MyApplication.getMarkwon());
        EditText editText = findViewById(R.id.add_post_connect);
        EditText title = findViewById(R.id.add_post_title);
        editText.addTextChangedListener(MarkwonEditorTextWatcher.withProcess(editor));

        RecyclerView recyclerView = findViewById(R.id.add_post_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        imageRecyclerViewAdapter = new ImageRecyclerViewAdapter(imageStatuses, this, new ListCallBack() {
            @Override
            public void click(String url) {
                editText.setText(editText.getText()+"\n![图片]("+url+")\n");
            }
        });
        recyclerView.setAdapter(imageRecyclerViewAdapter);

        findViewById(R.id.add_post_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qiNiuToken!=null){
                    Matisse.from(AddPostActivity.this)
                            .choose(MimeType.ofImage())
                            .countable(true)
                            .maxSelectable(9)
                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .theme(com.zhihu.matisse.R.style.Matisse_Zhihu)
                            .imageEngine(new GlideEngine())
                            .showPreview(false) // Default is `true`
                            .forResult(1);
                }else{
                    Toast.makeText(AddPostActivity.this, "正在初始化...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.add_post_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArray jsonArray = new JSONArray();
                for (ImageStatus imageStatus : imageStatuses){
                    if (imageStatus.getUrl()!=null){
                        jsonArray.put(imageStatus.getUrl());
                    }else{
                        Toast.makeText(AddPostActivity.this, "等待上传完成", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                PostDto postDto = new PostDto();
                postDto.setContent(editText.getText().toString());
                postDto.setTitle(title.getText().toString());
                postDto.setImages(jsonArray.toString());
                postDto.setTypeId(1);
                model.getAddPost(postDto);
            }
        });
        model.getQiNiuToken();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==11){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                init();
            }else{
                Toast.makeText(this, "请给权限", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK){
            List<Uri> list =  Matisse.obtainResult(data);
            for (Uri uri:list){
                ImageStatus imageStatus = new ImageStatus(uri,null);
                imageStatuses.add(imageStatus);
                uploadManager.put(uri, ContextGetter.applicationContext().getContentResolver(),null, qiNiuToken.getToken(),
                        new UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo info, JSONObject res) {
                                //res 包含 hash、key 等信息，具体字段取决于上传策略的设置
                                if(info.isOK()) {
                                    try {
                                        imageStatus.setUrl("http://rhzldubg8.hd-bkt.clouddn.com/"+res.getString("key"));
                                        imageRecyclerViewAdapter.notifyDataSetChanged();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, null);
            }
            imageRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showQiNiuToken(QiNiuToken token) {
        qiNiuToken = token;
    }

    @Override
    public void showAddPost(String message, Integer code) {
        if (code==200){
            Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "发布失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dissLoading() {

    }
}