package com.holder.sdk.eventmanger.internal.transfer;

import com.holder.sdk.eventmanger.internal.event.BaseEvent;
import com.holder.sdk.eventmanger.internal.exception.EventException;
import com.holder.sdk.eventmanger.internal.kernel.ISubscriber;
import com.holder.sdk.eventmanger.internal.kernel.ITransfer;
import com.holder.sdk.eventmanger.internal.subscriber.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Created by yangjian on 2018/4/6.
 */

public class TransferManager implements ITransfer {

    private Map<Subscriber, ISubscriber> mSubscribeMap;

    private Map<Class<? extends ISubscriber>, List<Subscriber>> mSubscriberClassMap;

    private List<BaseEvent> mQueue;

    private boolean isNotifySubscriber = false;

    public void onRefreshTransfer(Subscriber subscribe, ISubscriber iSubscribe) {

        if (subscribe == null || iSubscribe == null) {

            throw new EventException("subscribe or iSubscribe is null");
        }
        if (mSubscribeMap == null) {

            mSubscribeMap = new HashMap<>(4, 0.75f);
        }

        if (mSubscribeMap.containsKey(subscribe)) {
            throw new EventException("subscribe : " + subscribe + "  iSubscribe : " + iSubscribe +
                    " is in the map");
        }

        mSubscribeMap.put(subscribe, iSubscribe);
    }

    public void onRemove(Subscriber subscribe) {

        if (mSubscribeMap != null && mSubscribeMap.containsKey(subscribe)) {

            mSubscribeMap.remove(subscribe);
        }
    }

    @Override
    public void onAllRemove() {

        if (mSubscribeMap != null) {

            mSubscribeMap.clear();
        }

        if (mSubscriberClassMap != null) {

            mSubscriberClassMap.clear();
        }

        if (mQueue != null) {

            mQueue.clear();
        }
    }

    @Override
    public void addEvent(BaseEvent event) {
        if (mQueue == null) {
            mQueue = new LinkedList<>();
        }
        if (mQueue.size() > 5 || mQueue.indexOf(event) > -1) {
            return;
        }
        mQueue.add(event);
        event.isUsered = false;
    }

    @Override
    public BaseEvent getEvent() {

        if (mQueue != null && mQueue.size() > 0) {

            BaseEvent event = mQueue.get(0);
            mQueue.remove(event);
            return event;
        }
        return null;
    }


    public void onMessageEvent(Subscriber subscribe, BaseEvent event) {

        if (mSubscribeMap != null && mSubscribeMap.containsKey(subscribe)) {
            ISubscriber iSubscriber = mSubscribeMap.get(subscribe);
            iSubscriber.onMessageEvent(event);
            addEvent(event);
        }
    }

    @Override
    public void notifySubscriber(BaseEvent object, Class<?>... cls) {
        checkNull(cls);
        if (mSubscriberClassMap != null && mSubscriberClassMap.size() > 0) {
            for (Class<?> cl : cls) {

                HashMap<Class<? extends ISubscriber>, List<Subscriber>> map = new HashMap<>();
                map.putAll(mSubscriberClassMap);

                for (Map.Entry<Class<? extends ISubscriber>, List<Subscriber>> entry : map.entrySet()) {
                    Class<? extends ISubscriber> mapClcl = entry.getKey();
                    if (cl.isAssignableFrom(mapClcl)) {
                        List<Subscriber> subscribers = entry.getValue();
                        if (subscribers != null && subscribers.size() > 0) {
                            for (Subscriber subscriber : subscribers) {
                                onMessageEvent(subscriber, object);
                            }
                        }
                    }
                }
//                for (Class<? extends ISubscriber> mapClcl : mSubscriberClassMap.keySet()) {
//                    if (cl.isAssignableFrom(mapClcl)) {
//                        List<Subscriber> subscribers = mSubscriberClassMap.get(mapClcl);
//                        if (subscribers != null && subscribers.size() > 0) {
//                            for (Subscriber subscriber : subscribers) {
//                                onMessageEvent(subscriber, object);
//                            }
//                        }
//                    }
//                }
            }
        }
    }

    @Override
    public void notifyAllSubscriber(BaseEvent object) {

        HashMap<Class<? extends ISubscriber>, List<Subscriber>> map = new HashMap<>();
        map.putAll(mSubscriberClassMap);
        for (Class<? extends ISubscriber> cl : map.keySet()) {
            List<Subscriber> subscribers = map.get(cl);
            if (subscribers != null && subscribers.size() > 0) {
                for (Subscriber subscriber : subscribers) {
                    onMessageEvent(subscriber, object);
                }
            }
        }
    }

    private void checkNull(Object ob) {

        if (ob == null) {
            throw new NullPointerException("checkNull is null");
        }
    }

    @Override
    public void addSubscriber(ISubscriber iSubscribe) {
        if (mSubscriberClassMap == null) {
            mSubscriberClassMap = new HashMap<>(4, 0.75f);
        }
        Subscriber subscriber = new Subscriber(iSubscribe);
        if (mSubscriberClassMap.containsKey(iSubscribe.getClass())) {
            List<Subscriber> oldSubscribers = mSubscriberClassMap.get(iSubscribe.getClass());
            if (oldSubscribers != null) {
                for (Subscriber oldSubscriber : oldSubscribers) {
                    if (oldSubscriber.equals(subscriber)) {
                        throw new EventException("ISubscribe : " + iSubscribe.getClass() + " is already registerObserver");
                    }
                }
                oldSubscribers.add(subscriber);
            } else {
                List<Subscriber> list = new ArrayList<>();
                list.add(subscriber);
                mSubscriberClassMap.put(iSubscribe.getClass(), list);
            }
        } else {
            List<Subscriber> list = new ArrayList<>();
            list.add(subscriber);
            mSubscriberClassMap.put(iSubscribe.getClass(), list);
        }
        onRefreshTransfer(subscriber, iSubscribe);
    }

    @Override
    public void removeSubscriber(ISubscriber iSubscribe) {
        if (mSubscriberClassMap == null) {
            return;
        }
        if (mSubscriberClassMap.containsKey(iSubscribe.getClass())) {
            List<Subscriber> subscribers = mSubscriberClassMap.get(iSubscribe.getClass());
            if (subscribers != null && subscribers.size() > 0) {
                Object[] objects = subscribers.toArray();
                for (Object object : objects) {
                    Subscriber subscriber = (Subscriber) object;
                    if (subscriber.equals(iSubscribe)) {
                        subscribers.remove(subscriber);
                        onRemove(subscriber);
                    }
                }
            }
        }
    }
}
