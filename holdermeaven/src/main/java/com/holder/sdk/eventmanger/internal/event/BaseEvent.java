package com.holder.sdk.eventmanger.internal.event;

import android.content.Context;
import android.support.annotation.Keep;

import com.holder.sdk.eventmanger.EventManager;

/**
 * @author Created by yangjian-ds3 on 2018/4/12.
 */
@Keep
public final class BaseEvent {

    private int mPosition = -1;

    private Object mData;

    public Class<?> mFromClass;

    public int mEventType = -1;

    public boolean isUsered = false;

    private BaseEvent(){}
    public void sendEvent(Context context , Class<?> targetClass){

        EventManager.getDefault().notifySubscriber(context,this,targetClass);
    }

    public BaseEvent setFromClass(Class<?> fromClass){
        this.mFromClass = fromClass;
        return this;
    }

    public Class<?> getFromClass(){

        return mFromClass;
    }


    public BaseEvent setPosition(int position){

        this.mPosition = position;
        return this;
    }

    public int getPosition(){

        return mPosition;
    }

    public BaseEvent setData(Object data){

        this.mData = data;
        return this;
    }

    public Object getData(){

        return mData;
    }

    public BaseEvent setEventType(int eventType){
        this.mEventType = eventType;
        return this;
    }

    public int getEventType(){

        return mEventType;
    }

    public static BaseEvent builder(Context context){
        if(context == null){
            throw new NullPointerException("context is null");
        }
        BaseEvent event = EventManager.getDefault().obtain(context);
        if(event == null || event.isUsered){
            event = new BaseEvent();
        }else{
            event.clear();
        }
        event.isUsered = true;
        return event;
    }

    void clear(){

          mPosition = -1;

          mData = null;

          mFromClass = null;

          mEventType = -1;
    }
}
