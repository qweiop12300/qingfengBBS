package com.chenbaolu.qflt.MVP.Presenter;

import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.dto.UserDto;
import com.chenbaolu.baselib.network.bean.pojo.UserData;

/**
 * 描述 :
 * 创建时间 : 2022/9/9 21:20
 * 作者 : 23128
 */
public interface LoginPresenter {
    interface View extends BasePresenter.BaseView {
        void loginSuccess(UserData userData);
        void loginFailed(String message,Integer code);
        void registeredSuccess(String s);
        void registeredFailed(String message,Integer code);
    }
    interface Model extends BasePresenter.BaseModel{
        void login(UserDto userDto);
        void registered(UserDto userDto);
    }
}
