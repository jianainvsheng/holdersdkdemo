package com.demo.holder.tview.tablayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TabLayout for title tab.
 *
 * @author Created by yangjian-ds3 on 2018/4/10.
 */
public class TabLayout extends HorizontalScrollView implements OnClickListener {

    private int mNormalColor = Color.parseColor("#00050A");

    private int mSelectedColor = Color.parseColor("#01C990");

    private int mTabWidthPrelong = getDipSize(20);

    private int mLineOff = 0;

    private int mNormalBg = -1;

    private int mSelectedBg = -1;

    private TabStripLayout mTabStripLayout;
    private TextView mLastSelectedTab;

    private OnSelectedCallBack mOnSelectedCallBack;
    private boolean isNomalSelected;

    public interface OnSelectedCallBack {

        /**
         * 点击的回调
         *
         * @param position 点击的位置
         */
        void selected(TabLayout tabLayout, int position);
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        TabStripLayout tabStrip = new TabStripLayout(context);
        // Initialise paint.
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mSelectedColor);
        tabStrip.setPaint(paint);
        // Add tab strip view.
        addTabStrip(tabStrip);
    }

    private void addTabStrip(TabStripLayout tabStrip) {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        int width = LayoutParams.WRAP_CONTENT;
        int height = LayoutParams.MATCH_PARENT;
        addView(tabStrip, width, height);
        mTabStripLayout = tabStrip;
    }

    /**
     * Set tab normal color.
     */
    public void setNormalColor(int normalColor) {
        mNormalColor = normalColor;
    }

    /**
     * Set tab selected color.
     */
    public void setSelectedColor(int selectedColor) {
        mSelectedColor = selectedColor;
    }

    public void setMinSize(int minSize) {
        if (mTabStripLayout != null) {
            mTabStripLayout.setMinSize(minSize);
        }
    }

    /**
     * Add tab to strip
     *
     * @param index The index of tab.
     * @param title The title of tab.
     */
    public void addTab(int index, String title) {
        addTab(index,title,-1);
    }


    /**
     * Add tab to strip
     *
     * @param index The index of tab.
     * @param title The title of tab.
     */
    public void addTab(int index, String title,int size) {
        int textSize = 15;
        TextView tab = new TextView(getContext());
        tab.setText(title);
        if (mSp != -1) {
            tab.setTextSize(TypedValue.COMPLEX_UNIT_SP,mSp);
        } else
            tab.setTextSize(textSize);
        tab.setTextColor(mNormalColor);
        tab.setGravity(Gravity.CENTER);
        tab.setOnClickListener(this);
        tab.setFocusable(true);
        tab.setTag(index);
        tab.setLayoutParams(createTabParams(index,size));
        if (this.mTextViewBgResId != -1) {
            tab.setBackgroundResource(mTextViewBgResId);
        }
        tab.setPadding(mTabWidthPrelong / 2, 0, mTabWidthPrelong / 2, 0);
        mTabStripLayout.addView(tab);
    }



    private int mTextViewBgResId = -1;

    public void setTextViewBg(@DrawableRes int resid) {

        this.mTextViewBgResId = resid;
    }

    public void setTextViewNormalBg(@DrawableRes int resid) {

        this.mNormalBg = resid;
    }

    public void setTextViewSelectedBg(@DrawableRes int resid) {

        this.mSelectedBg = resid;
    }

    private float mSp = -1f;

    public void setTextSize(float sp) {

        this.mSp = sp;
    }

    public void setLineOff(int offSize){

        this.mLineOff = getDipSize(offSize);
    }

    public void setLineColor(int resId){

        if(mTabStripLayout != null && mTabStripLayout.getLinePaint() != null){
            mTabStripLayout.getLinePaint().setColor(resId);
        }
    }

    public TabStripLayout getTabStripLayout(){

        return mTabStripLayout;
    }
    public void setTabWidthPrelong(int tabWidthPrelong){

        this.mTabWidthPrelong = getDipSize(tabWidthPrelong);
    }

    private int mTabWidthPrelongMar;

    private int mTabItemWidth = -1;

    public void setTabWidthPrelongMar(int tabWidthPrelongMar){

        this.mTabWidthPrelongMar = getDipSize(tabWidthPrelongMar);
    }

    public void setTabItemWidth(int tabItemWidth){

        this.mTabItemWidth = getDipSize(tabItemWidth);
    }

    public TextView getChildTextView(int position) {
        if (getChildCount() > 0 && getChildAt(0) instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) getChildAt(0);
            if (position < parent.getChildCount()) {
                return (TextView) parent.getChildAt(position);
            }
        }
        return null;
    }

    private LinearLayout.LayoutParams createTabParams(int position,int size) {
        int width = LayoutParams.WRAP_CONTENT;
        if(mTabItemWidth != -1){

            width = mTabItemWidth;
        }
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        if(mTabWidthPrelongMar != -1  && size != -1){
            if(position == 0){
                params.rightMargin = mTabWidthPrelongMar / 2;
            }else if(position == size -1){
                params.leftMargin = mTabWidthPrelongMar / 2;
            }else{
                params.leftMargin = mTabWidthPrelongMar / 2;
                params.rightMargin = mTabWidthPrelongMar / 2;
            }
        }
        return params;
    }

    public void initStrip() {
        initStrip(0);
    }

    /**
     * Initialise strip since tabs added.
     */
    public void initStrip(int position) {
        if (mTabStripLayout.getChildCount() <= position) {
            mTabStripLayout.setRectF(new RectF());
            return;
        }
        // Select the first tab.
        mLastSelectedTab = (TextView) mTabStripLayout.getChildAt(position);
        mLastSelectedTab.setTextColor(mSelectedColor);
        post(new Runnable() {
            @Override
            public void run() {
                int w = mLastSelectedTab.getMeasuredWidth();
                int h = getHeight();
                float left = mLastSelectedTab.getLeft() + mTabWidthPrelong / 2 + mLineOff /2;
                float top = h - getDipSize(2);
                float right = mLastSelectedTab.getLeft() + w - mTabWidthPrelong/2 - mLineOff /2;
                float bottom = h;
                RectF rectF = new RectF(left, top, right, bottom);
                // Initialise the first strip.
                mTabStripLayout.setRectF(rectF);
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            int specWidth = MeasureSpec.getSize(widthMeasureSpec);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(specWidth,
                    MeasureSpec.EXACTLY);
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            int minSize = getDipSize(50);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(minSize,
                    MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int getDipSize(int value) {
        int unit = TypedValue.COMPLEX_UNIT_DIP;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        value = (int) TypedValue.applyDimension(unit, value, metrics);
        return value;
    }

    @Override
    public void onClick(View view) {
        if (mLastSelectedTab == view) {
            if (mOnSelectedCallBack != null && isNomalSelected) {
                int position = (Integer) (view.getTag());
                mOnSelectedCallBack.selected(this, position);
            }
            return;
        }
        // Set selected.
        if (mLastSelectedTab != null) {
            mLastSelectedTab.setTextColor(mNormalColor);
            if(mNormalBg != -1){

                mLastSelectedTab.setBackgroundResource(mNormalBg);
            }
        }
        TextView currentTab = (TextView) view;
        currentTab.setTextColor(mSelectedColor);
        if(mSelectedBg != -1){

            currentTab.setBackgroundResource(mSelectedBg);
        }
        mLastSelectedTab = currentTab;
        // Call back.
        int position = (Integer) (view.getTag());
        mTabStripLayout.updateHorizontalStrip(position, mTabWidthPrelong + mLineOff);
        if (mOnSelectedCallBack != null) {
            mOnSelectedCallBack.selected(this, position);
        }
    }

    public void setLastView(TextView lastView){

        mLastSelectedTab = lastView;
    }
    public void clearChildView() {

        if (mTabStripLayout != null) {
            mTabStripLayout.removeAllViews();
        }
    }

    /**
     * Select tab by position.
     *
     * @param position The index of selected tab.
     */
    public void selectTab(int position) {
        View view = mTabStripLayout.getChildAt(position);
        view.performClick();
    }

    /**
     * Select tab by position and position offset.
     *
     * @param position       The index of selected tab.
     * @param positionOffset The position offset.
     */
    public void selectTab(int position, float positionOffset) {
        int lastPosition = mTabStripLayout.indexOfChild(mLastSelectedTab);
        if (lastPosition < 0) {
            return;
        }
        int nextPosition = -1;
        float nextP = 0;
        float offset = 0;
        if (lastPosition == position) {
            nextPosition = lastPosition + 1;
            nextP = positionOffset;
            offset = mLastSelectedTab.getWidth() * nextP;
        } else if (lastPosition > position) {
            nextPosition = lastPosition - 1;
            nextP = 1f - positionOffset;
            offset = -mLastSelectedTab.getWidth() * nextP;
        }
        View nextTab = mTabStripLayout.getChildAt(nextPosition);
        if (nextTab != null) {
            float left = mLastSelectedTab.getLeft() + offset;
            int width = nextTab.getMeasuredWidth();
            int parentWidth = getWidth();
            float newPosition = left - parentWidth / 2 + width / 2;
            // Update position.
            scrollTo((int) newPosition, 0);
        }else{
            fullScroll(View.FOCUS_RIGHT);
        }
    }

    public void setNomalSelected(boolean nomalSelected) {
        isNomalSelected = nomalSelected;
    }

    /**
     * Set outside callback.
     */
    public void setOnSelectedCallBack(OnSelectedCallBack callBack) {
        mOnSelectedCallBack = callBack;
    }
}
