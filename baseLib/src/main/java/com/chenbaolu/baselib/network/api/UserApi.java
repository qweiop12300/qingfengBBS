package com.chenbaolu.baselib.network.api;

import com.chenbaolu.baselib.network.bean.BaseResult;
import com.chenbaolu.baselib.network.bean.dto.UserDto;
import com.chenbaolu.baselib.network.bean.pojo.QiNiuToken;
import com.chenbaolu.baselib.network.bean.pojo.UserAttention;
import com.chenbaolu.baselib.network.bean.pojo.UserData;

import java.util.List;
import java.util.Set;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @FormUrlEncoded
    @POST("getUserData")
    Observable<BaseResult<UserData>> getUserData(@Field("userId")Long userId);

    @POST("getUserDataList")
    Observable<BaseResult<List<UserData>>> getUserDataList(@Body Set<Long> list);

    @FormUrlEncoded
    @POST("attention")
    Observable<BaseResult<String>> attention(@Field("followed_user_id")Long userId);

    @POST("getUserAttention")
    Observable<BaseResult<List<UserAttention>>> getUserAttention(@Body UserAttention userAttention);

    @POST("getMyUserData")
    Observable<BaseResult<UserData>> getMyUserData();

    @POST("upUserData")
    Observable<BaseResult<String>> upUserData(@Body UserData userData);

    @POST("getQiNiuToken")
    Observable<BaseResult<QiNiuToken>> getQiNiuToken();

}
