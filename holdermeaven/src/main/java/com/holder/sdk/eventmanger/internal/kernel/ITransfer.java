package com.holder.sdk.eventmanger.internal.kernel;

import com.holder.sdk.eventmanger.internal.event.BaseEvent;

/**
 * @author Created by yangjian-ds3 on 2018/4/4.
 */

public interface ITransfer {

    /**
     * 通知注册的Subscriber
     * @param object
     * @param cls
     */
    void notifySubscriber(BaseEvent object, Class<?>... cls);

    /**
     * 通知所有的Subscriber
     * @param object
     */
    void notifyAllSubscriber(BaseEvent object);

    /**
     * 添加Subscriber
     * @param iSubscribe
     */
    void addSubscriber(ISubscriber iSubscribe);

    /**
     * 移除Subscriber
     * @param iSubscribe
     */
    void removeSubscriber(ISubscriber iSubscribe);

    /**
     * 移除所有的Subscriber
     */
    void onAllRemove();

    /**
     * 添加BaseEvent
     * @param event
     */
    void addEvent(BaseEvent event);

    /**
     * 拿取event
     * @return
     */
    BaseEvent getEvent();
}
