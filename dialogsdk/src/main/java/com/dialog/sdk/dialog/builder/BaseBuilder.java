package com.dialog.sdk.dialog.builder;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.StyleRes;

import com.dialog.sdk.dialog.commom.GNormalDialog;
import com.dialog.sdk.dialog.helper.BaseDialogHelper;

/**
 * Created by yangjian on 2018/3/22.
 */

@Keep
public abstract class BaseBuilder<D extends BaseBuilder<D>> implements IDialogBuilder<D>{


    private Class<? extends BaseDialogHelper<D>> mHelperClass;

    private @StyleRes
    int mThemeStyleId;

    private Context mContext;

    public BaseBuilder(Context context){

        this.mContext = context;
    }

    @Override
    public int getThemeStyleResId() {
        return mThemeStyleId;
    }

    @Override
    public D setThemeStyleResId(int themeStyleResId) {

        this.mThemeStyleId = themeStyleResId;
        return (D) this;

    }

    @Override
    public Class<? extends BaseDialogHelper<D>> getHelperClass() {
        return  mHelperClass;
    }

    @Override
    public D setHelperClass(Class<? extends BaseDialogHelper<D>> cls) {
        this.mHelperClass = cls;
        return (D) this;
    }

    @Override
    public Dialog build() {
        GNormalDialog<D> dialog;
        if(getThemeStyleResId() <= 0){
            dialog =  new GNormalDialog(getContext());
        }else{
            dialog =  new GNormalDialog(getContext(),getThemeStyleResId());
        }
        dialog.setBuilder((D)this);
        return dialog;
    }

    public Context getContext(){

        return mContext;
    }
}
