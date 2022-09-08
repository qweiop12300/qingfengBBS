package com.chenbaolu.baselib.network.api;

import com.chenbaolu.baselib.network.bean.BaseResult;
import com.chenbaolu.baselib.network.bean.dto.UserDto;
import com.chenbaolu.baselib.network.bean.pojo.UserData;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * 描述 :
 * 创建时间 : 2022/9/8 10:08
 * 作者 : 23128
 */
public interface UserApi {
    @POST("loginUser")
    Observable<BaseResult<UserData>> loginUser(@Body UserDto userDto);
    @POST("registerUser")
    Observable<BaseResult<String>> registerUser(@Body UserDto userDto);
    @POST("getUserData")
    Observable<BaseResult<UserData>> getUserData(@Field("userId")Long userId);
    @POST("getMyUserData")
    Observable<BaseResult<UserData>> getMyUserData();
    @POST("upUserData")
    Observable<BaseResult<String>> upUserData(@Body UserData userData);
}