package com.chenbaolu.qflt.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;

import com.chenbaolu.qflt.CallBack.PostCommentsCallBack;
import com.chenbaolu.qflt.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * 描述 :
 * 创建时间 : 2022/9/11 18:16
 * 作者 : 23128
 */
public class PostCommentsBottomDialog extends BottomSheetDialog{

    private PostCommentsCallBack postCommentsCallBack;
    private Context context;
    private String name;

    public PostCommentsBottomDialog(@NonNull Context context,String name, PostCommentsCallBack postCommentsCallBack) {
        super(context);
        this.context = context;
        this.postCommentsCallBack = postCommentsCallBack;
        this.name = name;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_post_comments);
        EditText editText = findViewById(R.id.dialog_comments_edit);
        editText.setHint("回复："+name);
        
        Button button = findViewById(R.id.dialog_comments_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postCommentsCallBack.backContent(editText.getText().toString());
                PostCommentsBottomDialog.this.dismiss();
            }
        });
    }

}
