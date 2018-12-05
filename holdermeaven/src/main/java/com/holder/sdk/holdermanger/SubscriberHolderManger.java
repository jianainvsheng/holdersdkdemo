package com.holder.sdk.holdermanger;

import android.content.Context;
import android.support.annotation.Keep;

import java.util.ArrayList;
import java.util.List;

import com.holder.sdk.eventmanger.EventManager;
import com.holder.sdk.holdermanger.holder.BaseSubscriberHolder;

/**
 * @author Created by yangjian-ds3 on 2018/4/10.
 */
@Keep
public class SubscriberHolderManger {

    private List<BaseSubscriberHolder<?>> mHolders;

    public SubscriberHolderManger(){

        mHolders = new ArrayList<>();
    }

    public void registerObserver(Context context, BaseSubscriberHolder<?> holder){
        EventManager.getDefault().registerObserver(context,holder);
        mHolders.add(holder);
    }

    public void removeObserver(Context context){

        for(BaseSubscriberHolder<?> holder : mHolders){
            EventManager.getDefault().removeObserver(context,holder);
        }
        mHolders.clear();
    }
}
