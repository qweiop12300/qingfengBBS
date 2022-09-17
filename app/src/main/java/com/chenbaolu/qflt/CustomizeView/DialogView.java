package com.chenbaolu.qflt.CustomizeView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chenbaolu.qflt.R;

/**
 * 描述 :
 * 创建时间 : 2022/9/16 10:35
 * 作者 : 23128
 */
public class DialogView extends androidx.appcompat.widget.AppCompatTextView {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int color = Color.BLUE;
    private float angleHeight = 70;
    private float angleWidth = 30;
    private boolean dialog_direction = false;


    public DialogView(Context context) {
        super(context);
        init();
    }

    public DialogView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DialogView);
        color = typedArray.getColor(R.styleable.DialogView_dialog_color,Color.BLUE);
        angleHeight = typedArray.getFloat(R.styleable.DialogView_angle_height,70);
        angleWidth = typedArray.getColor(R.styleable.DialogView_angle_width,30);
        dialog_direction = typedArray.getBoolean(R.styleable.DialogView_dialog_direction,false);
        init();
    }

    public DialogView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    private void init(){
        mPaint.setStrokeWidth(1.5F);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (!dialog_direction){
            canvas.drawRoundRect(angleWidth,0,getWidth(),getHeight(),(float) 20,(float) 20,mPaint);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(0,angleHeight);
            path.lineTo(angleWidth, angleHeight+angleWidth/2);
            path.lineTo(angleWidth, angleHeight-angleWidth/2);
            path.lineTo(0, angleHeight);
            path.close();

            canvas.drawPath(path, mPaint);
        }else {
            canvas.drawRoundRect(0,0,getWidth()-angleWidth,getHeight(),(float) 20,(float) 20,mPaint);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(getWidth(),angleHeight);
            path.lineTo(getWidth()-angleWidth, angleHeight+angleWidth/2);
            path.lineTo(getWidth()-angleWidth, angleHeight-angleWidth/2);
            path.lineTo(getWidth(), angleHeight);
            path.close();

            canvas.drawPath(path, mPaint);
        }

        super.onDraw(canvas);

    }
}
