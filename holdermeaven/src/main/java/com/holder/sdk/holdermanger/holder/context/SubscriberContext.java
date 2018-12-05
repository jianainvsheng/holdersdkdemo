package com.holder.sdk.holdermanger.holder.context;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Keep;
import android.view.View;

import com.holder.sdk.holdermanger.holder.BaseSubscriberHolder;

/**
 * @author Created by yangjian-ds3 on 2018/4/14.
 */
@Keep
public interface SubscriberContext {

    /**
     * 初始化
     * @param holder
     */
    void onAttach(BaseSubscriberHolder<?> holder);

    /**
     * 移除
     * @param context
     */
    void onDetachedFromContext(Context context);

    /**
     *
     * @param resId
     */
    <T extends View> T onFindViewById(View contentView, @IdRes int resId, Class<T> cls);

}
