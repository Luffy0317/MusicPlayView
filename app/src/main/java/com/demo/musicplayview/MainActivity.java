package com.demo.musicplayview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SpectrumView mSvPlay;
    private ObjectAnimator mTranslationY;
    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_start = findViewById(R.id.tv_start);
        TextView tv_stop = findViewById(R.id.tv_stop);
        mSvPlay = findViewById(R.id.sv_play);

        tv_start.setOnClickListener(this);
        tv_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start:
                statAnim();
                break;
            case R.id.tv_stop:
                mTranslationY.cancel();
                break;
            default:
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //此处view已layout
        mHeight = mSvPlay.getHeight();
    }

    //执行动画
    private void statAnim() {
        //执行动画
        mTranslationY = ObjectAnimator.ofFloat(mSvPlay, "transY", 0, mHeight * 2);
        mTranslationY.setDuration(2000);
        mTranslationY.setRepeatMode(ValueAnimator.RESTART);
        mTranslationY.setRepeatCount(ValueAnimator.INFINITE);
        mTranslationY.setInterpolator(new LinearInterpolator());
        mTranslationY.start();
    }
}
