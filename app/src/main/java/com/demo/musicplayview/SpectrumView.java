package com.demo.musicplayview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Keep;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2019/4/30
 * autor:maguangkun
 */
public class SpectrumView extends View {

    private Paint mPaint;
    /**线宽*/
    private float mLineWidth;
    /**总高度*/
    private float mBottom;
    /**第一条线*/
    private float mLineFirstHeight;
    private float mLineSendHeight;
    private float mLineThridHeight;
    private float mLineForutHeight;
    private float transY;

    public float getTransY() {
        return  transY;
    }

    @Keep
    public void setTransY(float transY) {
        this.transY = transY;
        invalidate();
    }

    public SpectrumView(Context context) {
        super(context);
        init();
    }

    public SpectrumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpectrumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.color_eb2166));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始化高度
        mBottom = getMeasuredHeight() - getPaddingTop() -getPaddingBottom();
        mLineFirstHeight =mBottom * 0.2f;
        mLineSendHeight =mBottom *0.6f;
        mLineThridHeight =mBottom *0.4f;
        mLineForutHeight =mBottom * 0.8f;
        //初始化宽度 4个指针
        mLineWidth =(getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) / 7;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制第一个 上升
        canvas.drawRect(0, getUpHeight(mLineFirstHeight), mLineWidth, mBottom,mPaint);
        //下降
        canvas.drawRect(mLineWidth *2, getDownHeight(mLineSendHeight), mLineWidth *3, mBottom,mPaint);
        //上升
        canvas.drawRect(mLineWidth *4, getUpHeight(mLineThridHeight), mLineWidth *5, mBottom,mPaint);
        //下降
        canvas.drawRect(mLineWidth *6, getDownHeight(mLineForutHeight), mLineWidth *7, mBottom,mPaint);
    }


    /**
     * 获取刚开始上升的高的度
     * @param nowHeight 初始化的高度
     * @return 新的高度
     */
    private float getUpHeight(float nowHeight){
        if(Math.abs(nowHeight - transY) > mBottom){
            return mBottom * 2 - (transY - nowHeight);
        }else{
            return Math.abs(nowHeight - transY);
        }
    }

    /**
     * 获取刚开始下降的高的度
     * @param nowHeight 初始化的高度
     * @return 新的高度
     */
    private float getDownHeight(float nowHeight){
        if(nowHeight + transY > mBottom){
            return Math.abs(mBottom * 2 -(nowHeight + transY));
        }else{
            return nowHeight + transY;
        }
    }
}
