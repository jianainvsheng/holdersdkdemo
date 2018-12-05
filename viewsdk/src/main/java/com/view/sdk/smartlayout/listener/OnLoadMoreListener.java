package com.view.sdk.smartlayout.listener;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;

import com.view.sdk.smartlayout.api.RefreshLayout;

/**
 * 加载更多监听器
 * Created by SCWANG on 2017/5/26.
 */
@Keep
public interface OnLoadMoreListener {
    void onLoadMore(@NonNull RefreshLayout refreshLayout);
}
