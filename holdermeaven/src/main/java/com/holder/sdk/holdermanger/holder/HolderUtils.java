package com.holder.sdk.holdermanger.holder;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.Keep;
import android.support.v4.app.Fragment;
import android.view.View;

import java.lang.reflect.Constructor;

import com.holder.sdk.annotation.IHolder;
import com.holder.sdk.annotation.internal.IHolderInfo;
import com.holder.sdk.eventmanger.internal.kernel.ISubscriber;

/**
 * Created by yangjian on 2018/5/25.
 */

public class HolderUtils {

    /**
     * 获得注册的所有holder
     */
    @Keep
    public static void onFindHolder(ISubscriber object, IHolderManager manager){

        Class<?> cls = object.getClass();
        if(BaseSubscriberHolder.class.isAssignableFrom(cls) ||
                (Activity.class.isAssignableFrom(cls) && IHolderManager.class.isAssignableFrom(cls)) ||
        (Fragment.class.isAssignableFrom(cls)&& IHolderManager.class.isAssignableFrom(cls))){
            IHolder iHolder = cls.getAnnotation(IHolder.class);
            if(iHolder != null){
                IHolderInfo[] holders = iHolder.holders();
                for(IHolderInfo infoHolder : holders){
                    @IdRes int id = infoHolder.resId();
                    View view = null;
                    if(object instanceof Fragment){
                        Fragment fragment = (Fragment) object;
                        view = fragment.getView().findViewById(id);
                    }else if (object instanceof Activity){

                        Activity activity = (Activity) object;
                        view = activity.findViewById(id);
                    }else if(object instanceof BaseSubscriberHolder){

                        BaseSubscriberHolder subscriberHolder = (BaseSubscriberHolder) object;
                        view = subscriberHolder.getContentView().findViewById(id);
                    }
                    if(view == null){
                        throw new NullPointerException("传入的parentView或者子view的id有误");
                    }
                    onCreateHolder(view,manager,infoHolder.holderClass());
                }
            }
        }else{
            throw new NullPointerException("暂时只支持 BaseSubscriberHolder，BaseActivity，BaseFragment" +
                    "的子类");
        }
    }
    /**
     * 创建holder
     * @param cls
     */
    private static void onCreateHolder(View view, IHolderManager manager, Class<? extends BaseSubscriberHolder> cls){

        try {
            Constructor constructor = cls.getConstructor(View.class,IHolderManager.class);
            constructor.newInstance(view,manager);
        } catch (Exception e) {
            throw new NullPointerException(cls.getCanonicalName() + " is null");
        }
    }
}
