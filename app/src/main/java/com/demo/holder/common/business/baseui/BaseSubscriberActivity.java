package com.demo.holder.common.business.baseui;

import android.os.Bundle;

import com.holder.sdk.eventmanger.EventManager;
import com.holder.sdk.eventmanger.internal.kernel.ISubscriber;
import com.holder.sdk.holdermanger.holder.BaseSubscriberHolder;
import com.holder.sdk.holdermanger.holder.HolderUtils;
import com.holder.sdk.holdermanger.holder.IHolderManager;
import com.holder.sdk.holdermanger.holder.context.SubscriberContext;
import com.holder.sdk.holdermanger.holder.impl.SubscriberContextHolder;

import com.demo.holder.common.business.baseui.view.BaseMvpNormalView;
import com.demo.holder.common.ui.mvp.MvpBasePresenter;

/**
 * @author Created by yangjian-ds3 on 2018/4/10.
 */

public abstract class BaseSubscriberActivity<V extends BaseMvpNormalView, P extends MvpBasePresenter<V>> extends BaseMvpNormalActivity<V,P> implements IHolderManager,ISubscriber {

    private SubscriberContext mSubscriberContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscriberContext = new SubscriberContextHolder();
        EventManager.getDefault().registerObserver(this, this);
        setContentView(onCreateLayoutResId());
        HolderUtils.onFindHolder(this,this);
    }

    public abstract int onCreateLayoutResId();

    @Override
    public void addSubscriberHolder(BaseSubscriberHolder<?> holder){
        mSubscriberContext.onAttach(holder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSubscriberContext != null){
            mSubscriberContext.onDetachedFromContext(this);
        }
        EventManager.getDefault().removeObserver(this, this);
    }

    @Override
    public SubscriberContext getContextHolder() {
        return mSubscriberContext;
    }

}
