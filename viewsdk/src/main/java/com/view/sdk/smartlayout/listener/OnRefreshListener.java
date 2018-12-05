package com.view.sdk.smartlayout.listener;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;

import com.view.sdk.smartlayout.api.RefreshLayout;


/**
 * 刷新监听器
 * Created by SCWANG on 2017/5/26.
 */
@Keep
public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
