package com.demo.holder.common.ui.array.holder;

import android.view.View;

import com.holder.sdk.holder.BaseHolder;


/**
 * Created by yangjian on 2018/8/6.
 */

public class BaseArrayHolder<T> extends BaseHolder<T> {

    private int mType;

    public BaseArrayHolder( View view) {
        super(view);
    }

    public int getType(){

        return mType;
    }

    public void setType(int type){

        this.mType = type;
    }
}
