package com.chenbaolu.baselib.base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.R;

/**
 * 描述 :
 * 创建时间 : 2022/9/8 08:51
 * 作者 : 23128
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        BaseApplication.getActivityManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        BaseApplication.getActivityManager().removeActivity(this);
        super.onDestroy();
    }
}
