package com.view.sdk.magicindicator.buildins.commonnavigator.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.support.annotation.Keep;

import com.view.sdk.magicindicator.buildins.UIUtil;

/**
 * Created by yangjian on 2018/9/9.
 */
@Keep
public class HXLinePagerIndicator extends LinePagerIndicatorEx {
    public HXLinePagerIndicator(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int size = UIUtil.dip2px(getContext(), 28);

        if(getLineRect().right - getLineRect().left > size){
            int every = (int)((float)(getLineRect().right - getLineRect().left - size) /2);
            getLineRect().right = getLineRect().right - every;
            getLineRect().left = getLineRect().left + every;
        }

        LinearGradient lg = new LinearGradient(getLineRect().left, getLineRect().top, getLineRect().right, getLineRect().bottom,
                new int[]{Color.parseColor("#FF9055"), Color.parseColor("#FD4366")}, null, LinearGradient.TileMode.CLAMP);
        getPaint().setShader(lg);
        canvas.drawRoundRect(getLineRect(), getRoundRadius(), getRoundRadius(), getPaint());
    }
}