package com.view.sdk.banner.listener;


import android.support.annotation.Keep;

/**
 * 旧版接口，由于返回的下标是从1开始，下标越界而废弃（因为有人使用所以不能直接删除）
 */
@Deprecated
@Keep
public interface OnBannerClickListener {
    public void OnBannerClick(int position);
}
