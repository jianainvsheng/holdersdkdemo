package com.demo.holder.common.business.baseui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.demo.holder.common.business.baseui.view.BaseMvpNormalView;
import com.holder.sdk.eventmanger.EventManager;
import com.holder.sdk.eventmanger.internal.kernel.ISubscriber;
import com.holder.sdk.holdermanger.holder.BaseSubscriberHolder;
import com.holder.sdk.holdermanger.holder.HolderUtils;
import com.holder.sdk.holdermanger.holder.IHolderManager;
import com.holder.sdk.holdermanger.holder.context.SubscriberContext;
import com.holder.sdk.holdermanger.holder.impl.SubscriberContextHolder;

import com.demo.holder.common.ui.mvp.MvpBasePresenter;

/**
 * @author Created by yangjian-ds3 on 2018/4/10.
 */

public abstract class BaseSubscriberFragment<V extends BaseMvpNormalView, P extends MvpBasePresenter<V>> extends BaseMvpNormalFragment<V,P> implements IHolderManager, ISubscriber {

    private SubscriberContext mSubscriberContext;

    public BaseSubscriberFragment() {
        mSubscriberContext = new SubscriberContextHolder();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventManager.getDefault().registerObserver(getContext(), this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HolderUtils.onFindHolder(this,this);
    }

    @Override
    public void addSubscriberHolder(BaseSubscriberHolder<?> holder) {
        mSubscriberContext.onAttach(holder);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscriberContext != null) {
            mSubscriberContext.onDetachedFromContext(getContext());
        }
        EventManager.getDefault().removeObserver(getContext(), this);
    }


    @Override
    public SubscriberContext getContextHolder() {
        return mSubscriberContext;
    }
}
