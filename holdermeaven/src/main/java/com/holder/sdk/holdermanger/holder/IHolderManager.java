package com.holder.sdk.holdermanger.holder;

import android.support.annotation.Keep;

import com.holder.sdk.holdermanger.holder.context.SubscriberContext;

/**
 * @author Created by yangjian-ds3 on 2018/4/14.
 */
@Keep
public interface IHolderManager {

    /**
     * 得到holder
     * @return
     */
    SubscriberContext getContextHolder();

    /**
     * 添加holder
     * @param holder
     */
    void addSubscriberHolder(BaseSubscriberHolder<?> holder);

}
