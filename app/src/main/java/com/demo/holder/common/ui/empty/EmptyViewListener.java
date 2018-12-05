package com.demo.holder.common.ui.empty;

/**
 * Created by wulei on 2015/6/8.
 */
public interface EmptyViewListener {

    /**
     * 无网络时调用
     */
    public void showNoNetConnLayout();

    /**
     * 数据加载失败时调用
     */
    public void showLoadFailedLayout();

    /**
     * 数据加载成功时调用
     */
    public void hideAll();
}
