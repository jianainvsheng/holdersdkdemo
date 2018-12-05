package com.holder.sdk.holdermanger.holder;

import android.support.annotation.IdRes;
import android.support.annotation.Keep;
import android.view.View;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import com.holder.sdk.eventmanger.internal.event.BaseEvent;
import com.holder.sdk.eventmanger.internal.exception.EventException;
import com.holder.sdk.eventmanger.internal.kernel.ISubscriber;
import com.holder.sdk.holder.BaseHolder;
import com.holder.sdk.holdermanger.holder.context.SubscriberContext;

/**
 * @author Created by yangjian-ds3 on 2018/4/10.
 *
 */
@Keep
public abstract class BaseSubscriberHolder<D> extends BaseHolder<BaseEvent> implements ISubscriber,IHolder {

    private Class<?> mTargetClass;

    private SubscriberContext mSubscriberContext;

    private D mBuilderData;

    public BaseSubscriberHolder(View contentView, IHolderManager manager) {
        super(contentView);
        init(manager);
        mTargetClass = manager.getClass();
        HolderUtils.onFindHolder(this,manager);
    }

    @Override
    public void setData(BaseEvent data) {
        super.setData(data);
        Class<D> dClass = getEventClass();
        if(data.getData() != null && dClass != null &&
                dClass.isAssignableFrom(data.getData().getClass())){
            D d = (D) data.getData();
            mBuilderData = d;
            builder(data,d);
        }
    }

    public D getBuilderData() {
        return mBuilderData;
    }

    @Override
    public void onMessageEvent(BaseEvent event) {
        setData(event);
    }

    private void init(IHolderManager manager){
        if (manager == null) {
            throw new EventException("SubscriberHolder: manager is null");
        }
        SubscriberContext contextHolder = manager.getContextHolder();
        if(contextHolder == null){
            throw new NullPointerException("SubscriberHolder:  contextHolder is null");
        }
        manager.addSubscriberHolder(this);
        mSubscriberContext = manager.getContextHolder();
    }

    public Class<?> getTargetClass(){
        return mTargetClass;
    }

    public Class<D> getEventClass(){

        Type type = getClass().getGenericSuperclass();
        if(type == null){
            return null;
        }
        if(type.toString().contains("<")
                && type.toString().contains(">")){
            Type[] tClass = ((ParameterizedType)getClass().
                    getGenericSuperclass()).getActualTypeArguments();
            if(tClass != null
                    && tClass.length >= 1
                    && tClass[0].toString().contains("class")){
                Class<D> cls = (Class<D>) tClass[0];
                return cls;
            }
        }
        return null;
    }

    @Override
    public <T extends View> T onFindView(@IdRes int resId, Class<T> cls) {

        return mSubscriberContext.onFindViewById(getContentView(),resId,cls);
    }

    /**
     * 根据数据构建ui
     * @param data
     */
    @Deprecated
    public abstract void builder(BaseEvent event, D data);
}
