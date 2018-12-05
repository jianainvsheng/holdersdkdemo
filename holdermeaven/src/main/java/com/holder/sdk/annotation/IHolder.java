package com.holder.sdk.annotation;

import android.support.annotation.Keep;

import com.holder.sdk.annotation.internal.IHolderInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjian on 2018/5/25.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Keep
public @interface IHolder {

    IHolderInfo[] holders();
}
