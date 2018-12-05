package com.demo.holder.tview.search;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.demo.holder.R;
import com.demo.holder.common.ui.array.widget.base.BaseArrayLayout;

/**
 * Created by yangjian on 2018/8/3.
 */

public class SearchLayout extends BaseArrayLayout {

    private int mLeft = 0;

    private int mDistance = 0;

    private int mRight = 0;

    private int mTop;

    private int mBottom;

    private ValueAnimator mVaAnim;

    public SearchLayout(Context context) {
        this(context,null);
    }

    public SearchLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SearchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
      //  mLeft = (int) context.getResources().getDimension(R.dimen.margin_5dp);
        mRight = (int) context.getResources().getDimension(R.dimen.widget_dp_30);
        mDistance = (int) context.getResources().getDimension(R.dimen.widget_dp_7);
//        mTop = (int) context.getResources().getDimension(R.dimen.margin_riv_add_group);
    //    mBottom = (int) context.getResources().getDimension(R.dimen.margin_riv_add_group);
    }

    public void setLayoutLett(int left){

        this.mLeft = left;
    }

    public void setLayoutRight(int right){

        this.mRight = right;
    }

    public void setLayoutTop(int top){

        this.mTop = top;
    }

    public void setLayoutBottom(int bottom){

        this.mBottom = bottom;
    }

    public void setLayoutDistance(int distance){

        this.mDistance = distance;
    }

    @Override
    public int onHeigthAdd() {
        return mBottom + mTop;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = 0;
        int width = mLeft + mRight;
        if(getChildCount() > 0){
            for(int i = 0 ; i < getChildCount() ; i ++){
                View view = getChildAt(i);
                view.measure(0,0);
                height = height > view.getMeasuredHeight() ? height : view.getMeasuredHeight();
                width += view.getMeasuredWidth() + (i == getChildCount() - 1 ? 0 :mDistance);
            }
        }
        setMeasuredDimension(width, getDefaultViewSize(height,heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int width = mLeft;
        for(int i = 0 ; i < getChildCount() ; i ++){
            View view = getChildAt(i);
            view.layout(width,
                    (getMeasuredHeight() - view.getMeasuredHeight())/2,
                    width + view.getMeasuredWidth(),
                    (getMeasuredHeight() + view.getMeasuredHeight())/2);
            width += view.getWidth() + mDistance;
        }
        checkPositionSize(width);
    }

    private void smoothScrollTo(final ViewGroup viewGroup, final int startX, final int destX, long duration){
        this.invalidate();
        if(this.mVaAnim != null){
            this.mVaAnim.cancel();
            this.mVaAnim.removeAllUpdateListeners();
            this.mVaAnim = null;
        }
        this.mVaAnim = ValueAnimator.ofFloat(1.0f);
        this.mVaAnim.setDuration(duration);
        this.mVaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int value = startX + (int)((float)(destX - startX)* (float)animation.getAnimatedValue());
                viewGroup.scrollTo(value,0);
            }
        });
        this.mVaAnim.start();
    }

    private void checkPositionSize(final int width){

        this.post(new Runnable() {
            @Override
            public void run() {
                if(getParent() == null || !(getParent() instanceof ViewGroup)){
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) getParent();
                if(width + mRight > viewGroup.getMeasuredWidth()){
                    smoothScrollTo(viewGroup,viewGroup.getScrollX(),width + mRight - viewGroup.getMeasuredWidth(),200);
                }
            }
        });
    }
    @Override
    public void addItemView(final View view) {
        super.addItemView(view);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f,1.0f);
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue=(float)valueAnimator.getAnimatedValue();
                view.setAlpha(animatedValue);
            }
        });
        valueAnimator.start();
    }
}
