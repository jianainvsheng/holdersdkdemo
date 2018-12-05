package com.holder.sdk.eventmanger.internal.subscriber;


import com.holder.sdk.eventmanger.internal.kernel.ISubscriber;

/**
 * @author Created by yangjian-ds3 on 2018/4/4.
 */

public class Subscriber {

    public Class<? extends ISubscriber> mTargetSub;

    public long mHashCode;

    public Subscriber(ISubscriber target){
        if(target == null){
            throw new NullPointerException("targetSub class is null");
        }
        this.mTargetSub = target.getClass();
        mHashCode = target.hashCode();
    }

    public Class<? extends ISubscriber> getTargetEventClass(){

        return mTargetSub;
    }
    @Override
    public boolean equals(Object other) {
        if (other instanceof Subscriber) {
            Subscriber otherSubscription = (Subscriber) other;
            return (mTargetSub == otherSubscription.mTargetSub &&
                    mHashCode == otherSubscription.mHashCode);
        } else {
            return (other.getClass() == mTargetSub && mHashCode == other.hashCode());
        }
    }

    @Override
    public int hashCode() {
        return mTargetSub.hashCode();
    }
}
