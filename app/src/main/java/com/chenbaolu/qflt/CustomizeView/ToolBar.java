package com.chenbaolu.qflt.CustomizeView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.chenbaolu.qflt.R;


/**
 * 描述 :
 * 创建时间 : 2022/9/4 23:15
 * 作者 : 23128
 */
public class ToolBar extends Toolbar {

    public ToolBar(@NonNull Context context) {
        super(context);
    }

    public ToolBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ToolBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
