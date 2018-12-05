package com.holder.sdk.eventmanger.internal.kernel;

import android.support.annotation.Keep;

import com.holder.sdk.eventmanger.internal.event.BaseEvent;

/**
 * @author Created by yangjian-ds3 on 2018/4/4.
 */
@Keep
public interface ISubscriber {

    /**
     * receive messages
     * @param event
     */
    void onMessageEvent(BaseEvent event);
}
