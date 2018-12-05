package com.view.sdk.magicindicator.buildins.commonnavigator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;

import com.view.sdk.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicatorEx;

/**
 * Created by yangjian on 2018/9/9.
 */

public class HXFLinePagerIndicator extends LinePagerIndicatorEx {
    public HXFLinePagerIndicator(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LinearGradient lg = new LinearGradient(getLineRect().left, getLineRect().top, getLineRect().right, getLineRect().bottom,
                new int[]{Color.parseColor("#FF9055"), Color.parseColor("#FD4366")}, null, LinearGradient.TileMode.CLAMP);
        getPaint().setShader(lg);
        canvas.drawRoundRect(getLineRect(), getRoundRadius(), getRoundRadius(), getPaint());
    }
}