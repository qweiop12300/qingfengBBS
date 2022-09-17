package com.chenbaolu.qflt.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.base.BaseActivity;
import com.chenbaolu.baselib.network.bean.SocketBean.Message;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;
import com.chenbaolu.qflt.Adapter.DialogRecyclerAdapter;
import com.chenbaolu.qflt.MVP.Presenter.DialogPresenter;
import com.chenbaolu.qflt.MyApplication;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.RxBus.RxBus;
import com.chenbaolu.qflt.SQLite.MyDBUtil;
import com.chenbaolu.qflt.ui.service.MessageService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

@AndroidEntryPoint
public class DialogActivity extends BaseActivity implements DialogPresenter.View {

    Disposable disposable;

    @Inject
    DialogPresenter.Model model;

    private MessageService.MessageServiceIBinder iBinder;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinder = (MessageService.MessageServiceIBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        model.setModel(this);
        Intent intent  = getIntent();
        id = intent.getLongExtra("id",0);

        String token = MyApplication.getToken();
        if (token!=null&&!token.equals("")){
            bindService(new Intent(this, MessageService.class),serviceConnection ,BIND_AUTO_CREATE);
        }

        EditText editText = findViewById(R.id.dialog_edit);
        Button button = findViewById(R.id.dialog_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iBinder!=null){
                    Message message = new Message("send","新消息",new Message.Data(null,new UserNews(id,editText.getText().toString())));
                    iBinder.sendMessage(message);
                    editText.setText("");
                }
            }
        });

        Set<Long> set = new HashSet<>();
        set.add(id);
        set.add(BaseApplication.getUserId());
        model.getUserDataList(set);
    }

    @Override
    public void showUserDataList(List<UserData> list) {
        Map<Long,String> map = new HashMap<>();
        map.put(list.get(0).getUser_id(),list.get(0).getAvatar());
        map.put(list.get(1).getUser_id(),list.get(1).getAvatar());
        TextView textView = findViewById(R.id.tool_bar_title);
        textView.setText(list.get(0).getName());
        RecyclerView recyclerView = findViewById(R.id.dialog_rec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<UserNews> userNews = MyDBUtil.getUserNewsListAsc("user_id = ? or produce_user_id = ?",new String[]{String.valueOf(id),String.valueOf(id)});
        DialogRecyclerAdapter recyclerAdapter = new DialogRecyclerAdapter(this,userNews,map);
        recyclerView.setAdapter(recyclerAdapter);
        linearLayoutManager.scrollToPositionWithOffset(recyclerAdapter.getItemCount() - 1, Integer.MIN_VALUE);
        RxBus.getInstance().toObservable(Message.class).subscribe(new Observer<Message>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull Message message) {
                if (message.getType().equals("send")||message.getType().equals("mySend")){
                    if (message.getData().getNews().size()>0){
                        UserNews newNews = message.getData().getNews().get(0);
                        if (newNews.getUser_id()==id||newNews.getProduce_user_id()==id){
                            if (newNews.getType()==1){
                                userNews.add(0,newNews);
                                recyclerAdapter.notifyDataSetChanged();
                                linearLayoutManager.scrollToPositionWithOffset(recyclerAdapter.getItemCount() - 1, Integer.MIN_VALUE);
                            }
                        }
                    }
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dissLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
        disposable.dispose();
    }
}