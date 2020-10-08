package com.ulfy.master.ui.custom_dkplayer;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DouyinLoadingBarView extends View {

    private Paint paint;

    /**view的初始颜色*/
    private String mColor = "ffffff";
    private int mHeight;
    private int mWidth;
    private int offset;
    private ValueAnimator valueAnimator;

    public void setColor(String color){
        mColor = color;
    }

    public DouyinLoadingBarView(Context context) {
        this(context,null,0);
    }

    public DouyinLoadingBarView(Context context , AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DouyinLoadingBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(4);
    }

    public void startAnimator(){

        if (valueAnimator!=null && valueAnimator.isRunning()){
            stopAnimator();
        }

        valueAnimator = ValueAnimator.ofInt(0, mWidth);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                offset = (int) valueAnimator.getAnimatedValue();
//                Log.e("tag","offset:"+offset);
                invalidate();
            }
        });
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(800);
        valueAnimator.start();
    }

    public void stopAnimator(){
        if (valueAnimator!=null){
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            valueAnimator = null;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getSize(200, widthMeasureSpec);
        mHeight = getSize(200, heightMeasureSpec);

        setMeasuredDimension(mWidth,mHeight);

        startAnimator();
    }

    public int getSize(int defaultSize,int measureSpace){

        int mode = MeasureSpec.getMode(measureSpace);
        int measureSize = MeasureSpec.getSize(measureSpace);

        int size = defaultSize;
        switch (mode){
            //没有限制模式
            case MeasureSpec.UNSPECIFIED:
                size = defaultSize;
                break;
            //最大模式：wrap_content
            case MeasureSpec.AT_MOST:
                size = measureSize;
                break;
            //精确模式:match_parent、具体大小
            case MeasureSpec.EXACTLY:
                size = measureSize;
                break;
        }

        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //计算颜色透明度变化
        int alph = 225 - offset * 225 / mWidth ;
        if (alph >225){
            alph = 225;
        }
        if (alph < 30){
            alph = 30;
        }

        String s = Integer.toHexString(alph);
        int currentColor = Color.parseColor("#" + s + mColor);

        paint.setColor(currentColor);

        //画线
        canvas.drawLine(mWidth / 2 - offset / 2,4,mWidth / 2 + offset / 2,4,paint);
    }
}