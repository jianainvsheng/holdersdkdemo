package com.demo.holder.common.business.baseui.view;

import android.app.Dialog;
import android.view.View;

import com.demo.holder.common.ui.empty.EmptyViewBox;
import com.demo.holder.common.ui.mvp.MvpView;


/**
 * @author Created by yangjian-ds3 on 2018/4/27.
 */

public interface BaseMvpNormalView extends MvpView {

    /**
     * 展示dialog
     */
    void showLoadingDialog();

    /**
     * 消失dialog
     */
    void hideLoadingDialog();

    /**
     * 初始化空布局 返回StatusLayoutManager.Builder
     * @param view
     * @return
     */
    void initStatusView(View view);


    /**
     * 初始化空布局 返回StatusLayoutManager.Builder
     * @param view
     * @param type
     */
    void initStatusView(View view, int type);


    /**
     * 对空布局进行设置
     * @param emptyView
     * @param type
     */
    void onBuiderStatus(EmptyViewBox emptyView, int type);
    /**
     * 展示没有数据的布局
     */
    void showEmptyLayout();

    /**
     * 展示错误布局
     */
    void showErrorLayout();

    /**
     * 展示没有网络的布局
     */
    void showNoNetLayout();

    /**
     * 暂时成功页面
     */
    void showSuccessLayout();


    /**
     * 网络错误
     * @param status
     */
    void onNetError(int status);

    /**
     * 获取dialog
     * @return
     */
    Dialog getDialog();
}
