package com.holder.sdk.holdermanger.holder.impl;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Keep;
import android.view.View;

import com.holder.sdk.holdermanger.holder.BaseSubscriberHolder;
import com.holder.sdk.holdermanger.SubscriberHolderManger;
import com.holder.sdk.holdermanger.holder.context.SubscriberContext;

/**
 * @author Created by yangjian-ds3 on 2018/4/10.
 */
@Keep
public class SubscriberContextHolder implements SubscriberContext {

    private SubscriberHolderManger mHoldersManager;

    public SubscriberContextHolder(){
        mHoldersManager = new SubscriberHolderManger();
    }
    @Override
    public void onAttach(BaseSubscriberHolder<?> holder) {

        mHoldersManager.registerObserver(holder.getContext(),holder);
    }

    @Override
    public void onDetachedFromContext(Context context) {
        mHoldersManager.removeObserver(context);
    }

    @Override
    public <T extends View> T onFindViewById(View contentView, @IdRes int resId, Class<T> cls) {

        if(contentView == null){

            throw new NullPointerException("onFindViewById 中 contentView is null");
        }

        View view = contentView.findViewById(resId);

        if(view == null){

            throw new NullPointerException("onFindViewById 中 contentView is no view of the resId ");
        }

        if(cls.isAssignableFrom(view.getClass())){

            return (T) view;
        }else
            throw new NullPointerException("onFindViewById 中 view 不是cls的子类或者接口 ");

    }
}
