package com.chenbaolu.baselib.CallBack;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.network.bean.BaseResult;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 描述 :
 * 创建时间 : 2022/9/8 19:54
 * 作者 : 23128
 */
public abstract class BaseObserver<T> implements Observer<T> {
    private LoadTasksCallBack loadTasksCallBack;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        Log.d("test1","su");
        loadTasksCallBack.onStart();
    }

    @Override
    public void onNext(@NonNull T t) {
        Log.d("test1","next");
        BaseResult baseResult = (BaseResult) t;
        if(baseResult.getCode()!=200){
            loadTasksCallBack.onFailed(baseResult.getMessage(), baseResult.getCode());
        }else{
            loadTasksCallBack.onSuccess(baseResult.getData());
        }
    }

    @Override
    public void onComplete() {
        Log.d("test1","co");
        loadTasksCallBack.onFinish();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.d("test1","error");
        Toast.makeText(BaseApplication.getContext(), "error", Toast.LENGTH_SHORT).show();
    }

    public void setLoadTasksCallBack(LoadTasksCallBack loadTasksCallBack){
        Log.d("test1","set");
        this.loadTasksCallBack = loadTasksCallBack;
    }
}
