package com.view.sdk.magicindicator.buildins.commonnavigator.titles;

import android.content.Context;
import android.support.annotation.Keep;

import com.view.sdk.magicindicator.buildins.ArgbEvaluatorHolder;


/**
 * 两种颜色过渡的指示器标题
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
@Keep
public class ColorTransitionPagerTitleView extends SimplePagerTitleView {

    public ColorTransitionPagerTitleView(Context context) {
        super(context);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        int color = ArgbEvaluatorHolder.eval(leavePercent, mSelectedColor, mNormalColor);
        setTextColor(color);

        System.out.println("==============onLeave========index:" + index + "leavePercent : " + leavePercent);
        if(mSelectedSize > mNormalSize){
            if(leavePercent < 0.1f){

                leavePercent = 0.0f;
            }else if(leavePercent > 0.9f){
                leavePercent = 1.0f;
            }
            float everySize = mSelectedSize - (mSelectedSize - mNormalSize) * leavePercent;
            setTextSize(everySize);

//            if(leavePercent == 1.0f){
//                setTextSize(14);
//                requestLayout();
//            }
        }

    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {

        System.out.println("==============onEnter========index:" + index + "enterPercent : " + enterPercent);
        int color = ArgbEvaluatorHolder.eval(enterPercent, mNormalColor, mSelectedColor);
        setTextColor(color);
        if(enterPercent < 0.15f){

            enterPercent = 0.0f;
        }else if(enterPercent > 0.85f){
            enterPercent = 1.0f;
        }
        if(mSelectedSize > mNormalSize){
            float everySize = (mSelectedSize - mNormalSize) * enterPercent + mNormalSize;
            setTextSize(everySize);

//            if(enterPercent == 1.0f){
//                setTextSize(16);
//                requestLayout();
//            }
        }

    }

    @Override
    public void onSelected(int index, int totalCount) {
    }

    @Override
    public void onDeselected(int index, int totalCount) {
    }
}
