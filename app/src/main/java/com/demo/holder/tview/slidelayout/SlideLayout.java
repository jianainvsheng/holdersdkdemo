package com.demo.holder.tview.slidelayout;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class SlideLayout extends FrameLayout {

    private View contentView;
    private View menuView;

    private int contentWidth;
    private int menuWidth;
    private int viewheight;

    private Scroller mScroller;

    private float downX;
    private float downY;

    /**
     * 系统认为的滑动的最小值
     */
    private int touchSlop;

    public SlideLayout(@NonNull Context context) {
        this(context, null);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
        menuView = getChildAt(1);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        contentWidth = contentView.getMeasuredWidth();
        menuWidth = menuView.getMeasuredWidth();
        viewheight = contentView.getMeasuredHeight();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 对子控件进行布局
        menuView.layout(contentWidth, 0, contentWidth + menuWidth, viewheight);
    }

    /**
     * true:拦截事件向子控件传递，然后调用自己的onTouch
     * false:事件会继续向子控件传递
     * @param event
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 记录初始坐标
                downX = event.getX();
                if (onStateChangeListener != null){
                    onStateChangeListener.onDown(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // 记录结束值
                float endX = event.getX();

                // 在 x 和 y 轴滑动的距离
                float dx = Math.abs(endX - downX);
                if (dx > touchSlop){
                    // 侧滑事件，不向子控件传递
                    intercept = true;
                }
                break;
        }


        return intercept;
    }

    private float startX;
    private float startY;

    private boolean isSliding;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 记录初始坐标
                downX = startX = event.getX();
                downY = startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 记录结束值
                float endX = event.getX();
                float endY = event.getY();
                // 计算偏移量
                float distanceX = endX - startX;

                int toScrollX = (int) (getScrollX() - distanceX);
                if (toScrollX < 0){
                    toScrollX = 0;
                }else if (toScrollX > menuWidth){
                    toScrollX = menuWidth;
                }

                startX = event.getX();
                startY = event.getY();

                // 在 x 和 y 轴滑动的距离
                float dx = Math.abs(endX - downX);
                float dy = Math.abs(endY - downY);
                if (dx > dy && dx > touchSlop){
                    isSliding = true;
                }

                if (isSliding){
                    // 响应侧滑，反拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                    scrollTo(toScrollX, getScrollY());
                }

                break;
            case MotionEvent.ACTION_UP:
                isSliding = false;
                int totalScrollX = getScrollX(); // 偏移量
                if (totalScrollX < menuWidth / 2){
                    // 关闭menu
                    closeMenu();
                }else {
                    // 打开menu
                    openMenu();
                }

                break;
        }
        return true;
    }

    public void closeMenu() {
        int distanceX = 0 - getScrollX();
        mScroller.startScroll(getScrollX(), getScrollY(), distanceX, getScrollY());
        invalidate();
        if (onStateChangeListener != null){
            onStateChangeListener.onClose(this);
        }
    }

    public boolean isOpenMenu(){

        return (getScrollX() == menuWidth);
    }
    public void openMenu() {
        int distanceX = menuWidth - getScrollX();
        mScroller.startScroll(getScrollX(), getScrollY(), distanceX, getScrollY());
        invalidate();
        if (onStateChangeListener != null){
            onStateChangeListener.onOpen(this);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }



    private OnStateChangeListener onStateChangeListener;

    /**
     * 事件回掉
     * @param onStateChangeListener
     */
    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener){
        this.onStateChangeListener = onStateChangeListener;
    }

    public interface OnStateChangeListener{
        void onClose(SlideLayout slideLayout);
        void onDown(SlideLayout slideLayout);
        void onOpen(SlideLayout slideLayout);
    }

}
