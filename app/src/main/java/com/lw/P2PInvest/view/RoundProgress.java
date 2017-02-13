package com.lw.P2PInvest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lw.P2PInvest.R;
import com.lw.P2PInvest.utils.UiUtils;

/**
 * Created by lw on 2017/2/13.
 */

public class RoundProgress extends View {


    private int width;
    private int height;
    private Paint paint;
    private float roundWidth;
    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private int max;
    private int progress;

    public RoundProgress(Context context) {
        this(context, null);
    }

    public RoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = this.getMeasuredWidth();
        height = this.getMeasuredHeight();
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);

        roundColor = typedArray.getColor(R.styleable.RoundProgress_roundColor, Color.GRAY);
        roundProgressColor = typedArray.getColor(R.styleable.RoundProgress_roundProgressColor, Color.RED);
        textColor = typedArray.getColor(R.styleable.RoundProgress_textColor, Color.GREEN);

        roundWidth = typedArray.getDimension(R.styleable.RoundProgress_roundWidth, UiUtils.dp2px(20));
        textSize = typedArray.getDimension(R.styleable.RoundProgress_textSize, UiUtils.dp2px(10));

        max = typedArray.getInteger(R.styleable.RoundProgress_max, 100);
        progress = typedArray.getInteger(R.styleable.RoundProgress_progress, 60);

        // 节省资源
        typedArray.recycle();

        //初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);//去除毛边

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //1.绘制圆环
        //获取圆心坐标
        int cx = width / 2;
        int cy = width / 2;
        float radius = width / 2 - roundWidth / 2;
        paint.setColor(roundColor);//设置画笔颜色
        paint.setStyle(Paint.Style.STROKE);//设置圆环的样式
        paint.setStrokeWidth(roundWidth);//设置圆环的宽度
        canvas.drawCircle(cx, cy, radius, paint);

        //2.绘制圆弧
        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2, width - roundWidth / 2, width - roundWidth / 2);
        paint.setColor(roundProgressColor);//设置画笔颜色
        canvas.drawArc(rectF, 0, progress * 360 / max, false, paint);

        //3.绘制文本
        String text = progress * 100 / max + "%";
        //设置paint
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        Rect rect = new Rect();//创建了一个矩形，此时矩形没有具体的宽度和高度
        //此时的矩形的宽度和高度才会有
        paint.getTextBounds(text, 0, text.length(), rect);
        //获取左下顶点的坐标
        int x = width / 2 - rect.width() / 2;
        int y = width / 2 + rect.height() / 2;

        canvas.drawText(text, x, y, paint);
    }
}
