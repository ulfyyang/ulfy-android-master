package com.ulfy.master.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.master.R;

public class CircleProgressView extends View {
    private int lineStrokeWidth;                // 圆弧线条宽度
    private int lineStrokeColor;                // 圆弧线条颜色
    private int textStrokeWidth;                // 字体线条宽度
    private int maxProgress;                    // 最大进度
    private int progress;                       // 当前进度
    private RectF circleRectF;                  // 圆弧绘制的路径
    private String textTop;                     // 中间百分比上方的提示文字
    private String textBottom;                  // 中间百分比下方的文字
    private Paint paint;                        // 绘制用的画笔
    private boolean showBackgroundCircle;       // 是否显示
    private boolean showTextPercent;            // 是否显示百分比文字
    private boolean showTextTop;                // 是否显示顶部文字
    private boolean showTextBottom;             // 是否显示底部文字

    public CircleProgressView(Context context) {
        super(context);
        lineStrokeWidth = 8;
        lineStrokeColor = Color.rgb(0xf8, 0x60, 0x30);
        textStrokeWidth = 2;
        maxProgress = 100;
        progress = 30;
        init(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        lineStrokeWidth = typedArray.getLayoutDimension(R.styleable.CircleProgressView_lineStrokeWidth, 8);
        lineStrokeColor = typedArray.getColor(R.styleable.CircleProgressView_lineStrokeColor, Color.rgb(0xf8, 0x60, 0x30));
        textStrokeWidth = 2;
        maxProgress = typedArray.getInteger(R.styleable.CircleProgressView_maxProgress, 100);
        progress = typedArray.getInteger(R.styleable.CircleProgressView_progress, 30);
        textTop = typedArray.getString(R.styleable.CircleProgressView_textTop);
        textBottom = typedArray.getString(R.styleable.CircleProgressView_textBottom);
        showBackgroundCircle = typedArray.getBoolean(R.styleable.CircleProgressView_showBackgroundCircle, false);
        showTextPercent = typedArray.getBoolean(R.styleable.CircleProgressView_showTextPercent, false);
        showTextTop = typedArray.getBoolean(R.styleable.CircleProgressView_showTextTop, false);
        showTextBottom = typedArray.getBoolean(R.styleable.CircleProgressView_showTextBottom, false);
        typedArray.recycle();
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        circleRectF = new RectF();
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override protected void onDraw(Canvas canvas) {
        int width = getWidth(), height = getHeight();

        if (width != height) {
            width = height = Math.min(width, height);
        }

        drawCircle(canvas, width, height);
        drawText(canvas, width, height);
    }

    private void drawCircle(Canvas canvas, int width, int height) {
        // 计算绘制圆弧的路径
        circleRectF.left = lineStrokeWidth / 2;
        circleRectF.top = lineStrokeWidth / 2;
        circleRectF.right = width - lineStrokeWidth / 2;
        circleRectF.bottom = height - lineStrokeWidth / 2;
        // 设置画笔
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineStrokeWidth);
        // 绘制圆圈，进度条背景
        if (showBackgroundCircle) {
            paint.setColor(Color.rgb(0xe9, 0xe9, 0xe9));
            canvas.drawArc(circleRectF, -90, 360, false, paint);
        }
        // 绘制圆弧，进度条进度
        paint.setColor(lineStrokeColor);
        canvas.drawArc(circleRectF, -90, ((float) progress / maxProgress) * 360, false, paint);
    }

    private void drawText(Canvas canvas, int width, int height) {
        // 绘制中间进度百分比
        if (showTextPercent) {
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(textStrokeWidth);
            paint.setColor(lineStrokeColor);
            String text = (int)((float)progress / maxProgress * 100) + "%";int textHeight = height / 4;
            paint.setTextSize(textHeight);
            int textWidth = (int) paint.measureText(text, 0, text.length());
            canvas.drawText(text, width / 2 - textWidth / 2, height / 2 + textHeight / 2, paint);
        }
        // 绘制百分比进度上方的文字
        if (showTextTop && !TextUtils.isEmpty(textTop)) {
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(textStrokeWidth);
            paint.setColor(Color.rgb(0x99, 0x99, 0x99));
            String text = textTop;int textHeight = height / 8;
            paint.setTextSize(textHeight);
            int textWidth = (int) paint.measureText(text, 0, text.length());
            canvas.drawText(text, width / 2 - textWidth / 2, height / 4 + textHeight / 2, paint);
        }
        // 绘制百分比进度下方的文字
        if (showTextBottom && !TextUtils.isEmpty(textBottom)) {
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(textStrokeWidth);
            String text = textBottom;int textHeight = height / 8;
            paint.setTextSize(textHeight);
            int textWidth = (int) paint.measureText(text, 0, text.length());
            canvas.drawText(text, width / 2 - textWidth / 2, 3 * height / 4 + textHeight / 2, paint);
        }
    }

    public void setProgress(int progress) {
        this.progress = progress;
        // UI线程中更新
        this.invalidate();
        // 非UI线程中更新
//        this.postInvalidate();
    }

    public void setTextTop(String textTop) {
        this.textTop = textTop;
        this.invalidate();
    }

    public void setTextBottom(String textBottom) {
        this.textBottom = textBottom;
        this.invalidate();
    }
}
