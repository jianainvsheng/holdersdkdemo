package com.holder.sdk.annotation.internal;

import android.support.annotation.IdRes;
import android.support.annotation.Keep;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.holder.sdk.holdermanger.holder.BaseSubscriberHolder;

/**
 * Created by yangjian on 2018/5/25.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Keep
public @interface IHolderInfo {

    @IdRes int resId();

    Class<? extends BaseSubscriberHolder<?>> holderClass();
}
