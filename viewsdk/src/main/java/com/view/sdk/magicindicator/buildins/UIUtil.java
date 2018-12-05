package com.view.sdk.magicindicator.buildins;

import android.content.Context;
import android.support.annotation.Keep;

/**
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
@Keep
public final class UIUtil {

    public static int dip2px(Context context, double dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}