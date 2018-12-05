package com.view.sdk.magicindicator.buildins.commonnavigator.titles;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.view.sdk.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;

/**
 * 通用的指示器标题，子元素内容由外部提供，事件回传给外部
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/7/3.
 */
public class CommonPagerTitleLayout extends LinearLayout implements IMeasurablePagerTitleView {
    private OnPagerTitleChangeListener mOnPagerTitleChangeListener;
    private ContentPositionDataProvider mContentPositionDataProvider;

    private ColorTransitionPagerTitleView mTextView;

    public CommonPagerTitleLayout(Context context) {
        super(context);
        mTextView = new ColorTransitionPagerTitleView(context);
        LayoutParams params = new LayoutParams(100,LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
//        params.width = 100;
//        params.height = LayoutParams.MATCH_PARENT;
        mTextView.setLayoutParams(params);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        if (mOnPagerTitleChangeListener != null) {
            mOnPagerTitleChangeListener.onSelected(index, totalCount);
        }
        mTextView.onSelected(index,totalCount);
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        if (mOnPagerTitleChangeListener != null) {
            mOnPagerTitleChangeListener.onDeselected(index, totalCount);
        }
        mTextView.onDeselected(index,totalCount);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        if (mOnPagerTitleChangeListener != null) {
            mOnPagerTitleChangeListener.onLeave(index, totalCount, leavePercent, leftToRight);
        }
        mTextView.onLeave(index,totalCount,leavePercent,leftToRight);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        if (mOnPagerTitleChangeListener != null) {
            mOnPagerTitleChangeListener.onEnter(index, totalCount, enterPercent, leftToRight);
        }
        mTextView.onEnter(index,totalCount,enterPercent,leftToRight);
    }


    public void setSelectedColor(int selectedColor) {
        this.mTextView.setSelectedColor(selectedColor);
    }

    public void setNormalColor(int normalColor) {

        this.mTextView.setNormalColor(normalColor);
    }

    public void setNormalSize(int normalSize) {

        this.mTextView.setNormalSize(normalSize);
    }
    public void setSelectedSize(int selectedSize)
    {
        this.mTextView.setSelectedSize(selectedSize);
    }

    public void setPosition(int position){

        this.mTextView.setPosition(position);
    }

    public void setText(String text){

        this.mTextView.setText(text);
    }
    @Override
    public int getContentLeft() {
        if (mContentPositionDataProvider != null) {
            return mContentPositionDataProvider.getContentLeft();
        }
        return getLeft();
    }

    @Override
    public int getContentTop() {
        if (mContentPositionDataProvider != null) {
            return mContentPositionDataProvider.getContentTop();
        }
        return getTop();
    }

    @Override
    public int getContentRight() {
        if (mContentPositionDataProvider != null) {
            return mContentPositionDataProvider.getContentRight();
        }
        return getRight();
    }

    @Override
    public int getContentBottom() {
        if (mContentPositionDataProvider != null) {
            return mContentPositionDataProvider.getContentBottom();
        }
        return getBottom();
    }

    /**
     * 外部直接将布局设置进来
     *
     * @param contentView
     */
    public void setContentView(View contentView) {
        setContentView(contentView, null);
    }

    public void setContentView(View contentView, LayoutParams lp) {
        removeAllViews();
        if (contentView != null) {
            if (lp == null) {
                lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
            addView(contentView, lp);
        }
    }

    public void setContentView(int layoutId) {
        View child = LayoutInflater.from(getContext()).inflate(layoutId, null);
        setContentView(child, null);
    }

    public OnPagerTitleChangeListener getOnPagerTitleChangeListener() {
        return mOnPagerTitleChangeListener;
    }

    public void setOnPagerTitleChangeListener(OnPagerTitleChangeListener onPagerTitleChangeListener) {
        mOnPagerTitleChangeListener = onPagerTitleChangeListener;
    }

    public ContentPositionDataProvider getContentPositionDataProvider() {
        return mContentPositionDataProvider;
    }

    public void setContentPositionDataProvider(ContentPositionDataProvider contentPositionDataProvider) {
        mContentPositionDataProvider = contentPositionDataProvider;
    }

    public interface OnPagerTitleChangeListener {
        void onSelected(int index, int totalCount);

        void onDeselected(int index, int totalCount);

        void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight);

        void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight);
    }

    public interface ContentPositionDataProvider {
        int getContentLeft();

        int getContentTop();

        int getContentRight();

        int getContentBottom();
    }
}
