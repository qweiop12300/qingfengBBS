package com.chenbaolu.qflt.CustomizeView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 描述 :
 * 创建时间 : 2022/9/11 20:10
 * 作者 : 23128
 */
public class EditText extends androidx.appcompat.widget.AppCompatEditText {

    public EditText(@NonNull Context context) {
        super(context);
    }

    public EditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(false);
        return super.dispatchTouchEvent(ev);
    }
}
