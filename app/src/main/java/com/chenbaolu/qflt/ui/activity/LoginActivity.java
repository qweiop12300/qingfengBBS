package com.chenbaolu.qflt.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.chenbaolu.baselib.base.BaseActivity;
import com.chenbaolu.baselib.base.BasePresenter;
import com.chenbaolu.baselib.network.bean.dto.UserDto;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.qflt.MVP.Presenter.Impl.LoginPresenterImpl;
import com.chenbaolu.qflt.MVP.Presenter.LoginPresenter;
import com.chenbaolu.qflt.MyApplication;
import com.chenbaolu.qflt.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity implements LoginPresenter.View {

    LoginPresenter.Model model = new LoginPresenterImpl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        model.setBaseView(this);
        findViewById(R.id.tool_bar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button login = findViewById(R.id.login_login);
        Button register = findViewById(R.id.login_register);

        TextInputLayout user_name = findViewById(R.id.login_user_name_layout);
        TextInputLayout pass_word = findViewById(R.id.login_user_password_layout);
        TextInputLayout email = findViewById(R.id.login_email_layout);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login.getText().toString().equals("去登录")){
                    email.setVisibility(View.GONE);
                    register.setText("去注册");
                    login.setText("登录");
                }else{
                    if(!validateUserName(user_name.getEditText().getText().toString())){
                        user_name.setErrorEnabled(true);
                        user_name.setError("用户名长度限制6~20");
                    }else if(!validatePassWord(pass_word.getEditText().getText().toString())){
                        pass_word.setErrorEnabled(true);
                        pass_word.setError("密码长度限制6~20");
                    }else{
                        user_name.setErrorEnabled(false);
                        pass_word.setErrorEnabled(false);
                        UserDto userDto = new UserDto();
                        userDto.setAccount(user_name.getEditText().getText().toString());
                        userDto.setPassword(pass_word.getEditText().getText().toString());
                        model.login(userDto);
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(register.getText().toString().equals("去注册")){
                    email.setVisibility(View.VISIBLE);
                    register.setText("注册");
                    login.setText("去登录");
                }else{
                    if(!validateUserName(user_name.getEditText().getText().toString())){
                        user_name.setErrorEnabled(true);
                        user_name.setError("用户名长度限制6~20");
                    }else if(!validatePassWord(pass_word.getEditText().getText().toString())){
                        pass_word.setErrorEnabled(true);
                        pass_word.setError("密码长度限制6~20");
                    }else if(!validateEmail(email.getEditText().getText().toString())){
                        email.setErrorEnabled(true);
                        email.setError("邮箱格式错误");
                    }else{
                        user_name.setErrorEnabled(false);
                        pass_word.setErrorEnabled(false);
                        email.setErrorEnabled(false);
                        UserDto userDto = new UserDto();
                        userDto.setEmail(email.getEditText().getText().toString());
                        userDto.setAccount(user_name.getEditText().getText().toString());
                        userDto.setPassword(pass_word.getEditText().getText().toString());
                        model.registered(userDto);
                    }
                }
            }
        });
    }

    private boolean validateUserName(String userName){
        return userName.length()>6&&userName.length()<20;
    }
    private boolean validatePassWord(String passWord){
        return passWord.length()>6&&passWord.length()<20;
    }
    private boolean validateEmail(String email){
        String emailMatcher="[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
        return Pattern.matches(emailMatcher,email);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    public void loginSuccess(UserData userData) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
        editor.putString("avatar",userData.getAvatar());
        editor.apply();
        finish();
    }

    @Override
    public void loginFailed(String message, Integer code) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registeredSuccess(String s) {
        Toast.makeText(this, "注册成功，请查看邮箱激活", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registeredFailed(String message, Integer code) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setModel(BasePresenter.BaseModel baseModel) {
        model = (LoginPresenter.Model) baseModel;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dissLoading() {

    }
}