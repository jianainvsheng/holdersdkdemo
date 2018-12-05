package com.demo.holder.common.business.baseui;

import android.os.Bundle;
import android.view.View;

import com.demo.holder.common.business.baseui.view.BaseMvpNormalView;
import com.demo.holder.common.ui.empty.EmptyViewBox;
import com.demo.holder.common.ui.mvp.MvpPresenter;

/**
 * @author Created by yangjian-ds3 on 2018/4/28.
 */

public abstract class BaseMvpNormalFragment<V extends BaseMvpNormalView, P extends MvpPresenter<V>> extends BaseMvpFragment<V, P> implements BaseMvpNormalView, EmptyViewBox.OnEmptyClickListener {

    private EmptyViewBox mStatusLayoutManager;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void initStatusView(View view) {
        initStatusView(view,-1);
    }

    @Override
    public void initStatusView(View view, int type) {
        mStatusLayoutManager = new EmptyViewBox(getContext(),view);
        onBuiderStatus(mStatusLayoutManager,type);
        mStatusLayoutManager.setOnEmptyClickListener(this);
    }

    @Override
    public void onBuiderStatus(EmptyViewBox emptyViewBox,int type) {

    }


    @Override
    public void showEmptyLayout() {

        if (mStatusLayoutManager != null) {
            mStatusLayoutManager.showNullDataLayout();
        }
    }

    @Override
    public void showErrorLayout() {
        if (mStatusLayoutManager != null) {

            mStatusLayoutManager.showNullDataLayout();
        }
    }

    @Override
    public void showNoNetLayout() {
        if (mStatusLayoutManager != null) {

            mStatusLayoutManager.showNoNetConnLayout();
        }
    }

    @Override
    public void showSuccessLayout() {
        if (mStatusLayoutManager != null) {

            mStatusLayoutManager.hideAll();
        }
    }

    @Override
    public void onNetError(int status) {

    }

}
