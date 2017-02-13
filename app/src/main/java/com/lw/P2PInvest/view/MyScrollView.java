package com.lw.P2PInvest.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import com.lw.P2PInvest.utils.UiUtils;

/**
 * Created by lw on 2017/2/13.
 */
public class MyScrollView extends ScrollView {

    private View linearView;
    private float eventY;
    private boolean isAnimationFinished = true;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int lastX, downX, downY;

    private int startX, startY, endX, endY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        int eventX = (int) ev.getX();
        int eventY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = downX = eventX;
                lastY = downY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                //获取水平和垂直方向的移动距离
                int absX = Math.abs(eventX - downX);
                int absY = Math.abs(eventY - downY);

                if(absY > absX && absY >= UiUtils.dp2px(10)){
                    isIntercept = true;//执行拦截
                }

                lastX = eventX;
                lastY = eventY;
                break;
        }

        return isIntercept;
    }

/**
 @Override public boolean onInterceptHoverEvent(MotionEvent event) {

 boolean intercept = false;

 int eventX = (int) event.getX();
 int eventY = (int) event.getX();

 switch (event.getAction()) {
 case MotionEvent.ACTION_DOWN:
 lastX = downX = eventX;
 lastY = downY = eventY;
 startX = (int) event.getX();
 startY = (int) event.getY();
 break;
 case MotionEvent.ACTION_MOVE:

 endX = (int) event.getX();
 endY = (int) event.getY();

 int dx = Math.abs(endX - startX);
 int dy = Math.abs(endY - startY);
 if (dy > dx && dy > UiUtils.dp2px(10)) {
 intercept = true;
 }

 startX = endX;
 startY = endY;

 //                int dx = eventX - downX;
 //                int dy = eventY - downY;
 //
 //                if (Math.abs(dx) < Math.abs(dy) && UiUtils.dp2px(10) < dy) {
 //                    intercept = true;
 //                }
 //
 //                lastX = eventX;
 //                lastY = eventY;
 break;
 }

 return intercept;
 }*/

    /**
     * 布局完成之后调用
     * 获取子视图
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        linearView = getChildAt(0);
    }

    private float lastY;

    Rect rect = new Rect();

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if ( linearView ==null && !isAnimationFinished) {
            return super.onTouchEvent(ev);
        }

        eventY = ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:

                if (isNeedMove()) {
                    //记录上下左右变量
                    if (rect.isEmpty()) { // 只记录一次
                        rect.set(linearView.getLeft(), linearView.getTop(), linearView.getRight(), linearView.getBottom());
                    }
                    //需要自己处理
                    int dy = (int) (eventY - lastY);
                    linearView.layout(linearView.getLeft(), linearView.getTop() + dy / 2, linearView.getRight(), linearView.getBottom() + dy / 2);
                }

                lastY = eventY;
                break;

            case MotionEvent.ACTION_UP:

                if (isNeedAnimation()) {
                    int translateY = linearView.getBottom() - rect.bottom;

                    TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -translateY);
                    translateAnimation.setDuration(500);
//                translateAnimation.setFillAfter(true);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isAnimationFinished = false;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            isAnimationFinished = true;
                            linearView.clearAnimation();
                            linearView.layout(rect.left, rect.top, rect.right, rect.bottom); // 重新布局
                            //情况rect
                            rect.setEmpty();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    linearView.startAnimation(translateAnimation);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public boolean isNeedAnimation() {
        return !rect.isEmpty();
    }

    /**
     * 回归ScrollView临界位置
     *
     * @return
     */
    private boolean isNeedMove() {
        int linearViewMeasuredHeight = linearView.getMeasuredHeight();

        int scrollViewMeasuredHeight = this.getMeasuredHeight();

        int dy = linearViewMeasuredHeight - scrollViewMeasuredHeight;

        Log.e("TAG", "linearViewMeasuredHeight:" + linearViewMeasuredHeight);
        Log.e("TAG", "scrollViewMeasuredHeight:" + scrollViewMeasuredHeight);
        Log.e("TAG", "dy:" + dy);
        int scrollY = this.getScrollY();

        Log.e("TAG", "scrollY:" + scrollY);
        if (scrollY <= 0 || scrollY >= dy)
            return true;
        return false;
    }
}
