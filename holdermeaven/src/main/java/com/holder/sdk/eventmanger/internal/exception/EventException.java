package com.holder.sdk.eventmanger.internal.exception;

import android.support.annotation.Keep;

/**
 * @author Created by yangjian-ds3 on 2018/4/4.
 */
@Keep
public class EventException extends RuntimeException {

    public EventException(String detailMessage) {
        super(detailMessage);
    }

    public EventException(Throwable throwable) {
        super(throwable);
    }

    public EventException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

}
