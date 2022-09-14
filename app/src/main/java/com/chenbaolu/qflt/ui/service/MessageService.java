package com.chenbaolu.qflt.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.network.bean.SocketBean.Message;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;
import com.chenbaolu.qflt.MyApplication;
import com.chenbaolu.qflt.RxBus.RxBus;
import com.chenbaolu.qflt.util.DateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 描述 :
 * 创建时间 : 2022/9/13 10:16
 * 作者 : 23128
 */
public class MessageService extends Service {
    private Gson gson = new GsonBuilder().registerTypeAdapter(Timestamp.class, new DateDeserializer()).create();

    private Binder binder = new MessageServiceIBinder();

    private SocketChannel socketChannel;

    class MessageServiceIBinder extends Binder {
        public void sendMessage(Message message){
            MessageService.this.sendMessage(message);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate() {
        super.onCreate();

        try {
            socketChannel = SocketChannel.open();
        }catch (Exception e){
            e.printStackTrace();
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    socketChannel.connect(new InetSocketAddress("192.168.249.105",5301));
                    Thread.sleep(2000);
                    if (socketChannel.isConnected()){
                        Log.d("test1",socketChannel.getLocalAddress().toString());
                        readMessage();
                        Message message = new Message("init","初始化",new Message.Data(MyApplication.getToken(), (UserNews) null));
                        sendMessage(message);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void readMessage(){
        if (socketChannel!=null&&socketChannel.isConnected()){
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        StringBuffer stringBuffer = new StringBuffer();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(524);
                        while (true){
                            int i = socketChannel.read(byteBuffer);
                            if(i==524){
                                byteBuffer.flip();
                                stringBuffer.append(StandardCharsets.UTF_8.decode(byteBuffer).toString());
                                byteBuffer.limit(524);
                                byteBuffer.position(0);
                            }else{
                                byteBuffer.flip();
                                stringBuffer.append(StandardCharsets.UTF_8.decode(byteBuffer).toString());
                                Message message = gson.fromJson(stringBuffer.toString(), Message.class);
                                RxBus.getInstance().post(message);
                                stringBuffer.setLength(0);
                                byteBuffer.limit(524);
                                byteBuffer.position(0);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void sendMessage(final Message message){
        if (socketChannel!=null&&socketChannel.isConnected()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        socketChannel.write(StandardCharsets.UTF_8.encode(gson.toJson(message)));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(socketChannel!=null&&socketChannel.isConnected()){
            try {
                Message message = new Message("close");
                sendMessage(message);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
