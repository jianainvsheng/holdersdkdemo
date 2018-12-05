package com.holder.sdk.holdermanger.holder;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by yangjian on 2018/5/26.
 */

public interface IHolder {

    /**
     * 获得view
     * @param resId
     * @param cls
     * @param <T>
     * @return
     */
    <T extends View> T onFindView(@IdRes int resId, Class<T> cls);
}
