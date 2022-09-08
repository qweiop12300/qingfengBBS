package com.chenbaolu.baselib.CallBack;

/**
 * 描述 :网络请求接口
 * 创建时间 : 2022/9/7 22:38
 * 作者 : 23128
 */
public interface LoadTasksCallBack<T> {
    void onSuccess(T t);
    void onStart();
    void onFailed(String message,Integer code);
    void onFinish();
}
