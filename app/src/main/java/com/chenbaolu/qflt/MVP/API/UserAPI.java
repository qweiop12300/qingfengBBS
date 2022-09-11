package com.chenbaolu.qflt.MVP.API;

import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.CallBack.BaseObserver;
import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.network.api.UserApi;
import com.chenbaolu.baselib.network.bean.BaseResult;
import com.chenbaolu.baselib.network.bean.dto.UserDto;
import com.chenbaolu.baselib.network.bean.pojo.UserAttention;
import com.chenbaolu.baselib.network.bean.pojo.UserData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 描述 :
 * 创建时间 : 2022/9/8 18:40
 * 作者 : 23128
 */


public class UserAPI {
    public static void loginUser(UserDto userDto, LoadTasksCallBack<UserData> loadTasksCallBack){
        BaseApplication.getRetrofit().create(UserApi.class).loginUser(userDto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<UserData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
    public static void registerUser(UserDto userDto, LoadTasksCallBack<String> loadTasksCallBack){
        BaseApplication.getRetrofit().create(UserApi.class).registerUser(userDto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }

    public static void getUserData(Long userId, LoadTasksCallBack<UserData> loadTasksCallBack){
        BaseApplication.getRetrofit().create(UserApi.class).getUserData(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<UserData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }

    public static void attention(Long userId, LoadTasksCallBack<String> loadTasksCallBack){
        BaseApplication.getRetrofit().create(UserApi.class).attention(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }


    public static void getUserAttention(UserAttention userAttention, LoadTasksCallBack<List<UserAttention>> loadTasksCallBack){
        BaseApplication.getRetrofit().create(UserApi.class).getUserAttention(userAttention)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<List<UserAttention>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }

    public static void getMyUserData(LoadTasksCallBack<UserData> loadTasksCallBack){
        BaseApplication.getRetrofit().create(UserApi.class).getMyUserData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<UserData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
    public static void upUserData(UserData userData, LoadTasksCallBack<String> loadTasksCallBack){
        BaseApplication.getRetrofit().create(UserApi.class).upUserData(userData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.setLoadTasksCallBack(loadTasksCallBack);
                        super.onSubscribe(d);
                    }
                });
    }
}
