package com.demo.holder.biness.time.builder;

import android.content.Context;

import com.dialog.sdk.dialog.builder.BaseBuilder;


/**
 * Created by yangjian on 2018/8/25.
 */

public class TimeBuilder extends BaseBuilder<TimeBuilder> {

    private long time;

    public TimeBuilder(Context context) {
        super(context);
    }

    public TimeBuilder setTime(long time){

        this.time = time;
        return this;
    }

    public long getTime(){

        return time;
    }
}
