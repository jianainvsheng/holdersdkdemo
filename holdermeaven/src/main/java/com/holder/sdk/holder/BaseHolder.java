package com.holder.sdk.holder;

import android.content.Context;
import android.support.annotation.Keep;
import android.view.View;

/**
 * @author Created by yangjian on 2017/7/7.
 */
@Keep
public class BaseHolder<T> {

    private View mContentView;

    private T mData;

    private Context mContext;

    private int mPosition = -1;

    public BaseHolder(View contentView) {

        this(null, contentView);
    }

    public BaseHolder(Context context, View contentView) {

        this.mContentView = contentView;
        this.mContext = context;
    }

    public void setData(T data) {

        this.mData = data;

    }

    public void setPosition(int position){

        this.mPosition = position;
    }

    public int getPosition(){

        return mPosition;
    }
    public T getData() {
        return mData;
    }

    public View getContentView() {
        return mContentView;
    }

    public Context getContext() {
        if (mContentView == null){
            return mContext;
        }
        return mContext == null ? mContentView.getContext() : mContext;
    }
}
