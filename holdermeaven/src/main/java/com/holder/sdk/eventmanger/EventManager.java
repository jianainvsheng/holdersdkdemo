package com.holder.sdk.eventmanger;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Keep;
import android.view.View;

import com.holder.sdk.eventmanger.internal.event.BaseEvent;
import com.holder.sdk.eventmanger.internal.kernel.ISubscriber;
import com.holder.sdk.eventmanger.internal.kernel.ITransfer;

import cn.tegele.com.common.R;

import com.holder.sdk.eventmanger.internal.exception.EventException;
import com.holder.sdk.eventmanger.internal.transfer.TransferManager;

/**
 * @author Created by yangjian-ds3 on 2018/4/4.
 */
@Keep
public class EventManager {
    static volatile EventManager defaultInstance;
    public static EventManager getDefault() {
        if (defaultInstance == null) {
            synchronized (EventManager.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventManager();
                }
            }
        }
        return defaultInstance;
    }

    /**
     * 根据名称注册观察者
     */
    public void registerObserver(Context context, ISubscriber observer) {

        checkNull(observer);
        addSubscriber(context,observer);
    }

    /**
     * 根据观察者反注册
     */
    public void removeObserver(Context context, ISubscriber observer) {

        checkNull(observer);
        removeSubscriber(context,observer);
    }

    /**
     * 清除观察者
     */
    public void clear(Context context) {
        defaultInstance = null;
        ITransfer transfer = getITransfer(context);
        if(transfer != null){
            transfer.onAllRemove();
        }
        clearActivity(context);
    }
    /**
     *
     * @param cls 被通知的
     * @return 返回值
     */

    public void notifySubscriber(Context context, BaseEvent object , Class<?>... cls) {
        checkNull(cls);
        ITransfer transfer = getITransfer(context);
        checkNull(transfer);
        transfer.notifySubscriber(object,cls);
    }

    public void notifyAllSubscriber(Context context, BaseEvent object) {
        ITransfer transfer = getITransfer(context);
        checkNull(transfer);
        transfer.notifyAllSubscriber(object);
    }

    private void checkNull(Object ob) {

        if (ob == null) {
            throw new NullPointerException("checkNull is null");
        }
    }

    private void addSubscriber(Context context, ISubscriber iSubscribe) {
        ITransfer transfer = getITransfer(context);
        checkNull(transfer);
        transfer.addSubscriber(iSubscribe);
    }

    private void removeSubscriber(Context context, ISubscriber iSubscribe) {

        ITransfer transfer = getITransfer(context);
        checkNull(transfer);
        transfer.removeSubscriber(iSubscribe);
    }

    private ITransfer getITransfer(Context context) {
        if (context == null) {
            throw new EventException("TransferManager: context is null");
        }
        if (!(context instanceof Activity)) {
            throw new EventException("TransferManager: context is not a Activity");
        }
        Activity activity = (Activity) context;
        View view = activity.findViewById(android.R.id.content);
        Object object = view.getTag(R.id.common_orderlist_event_transfer_id);
        if (object == null) {
            TransferManager manager = new TransferManager();
            view.setTag(R.id.common_orderlist_event_transfer_id, manager);
            object = manager;
        }
        if (!(object instanceof TransferManager)) {
            throw new EventException("TransferManager: object is not a TransferManager");
        }
        return (TransferManager) object;
    }

    private void clearActivity(Context context){
        if (context == null) {
            throw new EventException("TransferManager: context is null");
        }
        if (!(context instanceof Activity)) {
            throw new EventException("TransferManager: context is not a Activity");
        }
        Activity activity = (Activity) context;
        View view = activity.findViewById(android.R.id.content);
        view.setTag(R.id.common_orderlist_event_transfer_id, null);
    }

    public BaseEvent obtain(Context context){

        ITransfer transfer = getITransfer(context);
        checkNull(transfer);
        return transfer.getEvent();
    }
}
