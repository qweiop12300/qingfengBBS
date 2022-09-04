package com.chenbaolu.qflt.CustomizeView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 描述 :解决嵌套冲突
 * 创建时间 : 2022/9/4 21:44
 * 作者 : 23128
 */
public class RecyclerView extends androidx.recyclerview.widget.RecyclerView {

    private int downX= 0;
    private int downY= 0;

    public RecyclerView(@NonNull Context context) {
        super(context);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - downX;
                int deltaY = y - downX;
                //左右滑动
                if (Math.abs(deltaX) > Math.abs(deltaY)) {

                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            }
        }
        //赋值
        downX= x;
        downY= y;
        return super.dispatchTouchEvent(ev);
    }
}
