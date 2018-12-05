package com.demo.holder.biness.time;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.demo.holder.R;
import com.dialog.sdk.dialog.helper.BaseDialogHelper;
import com.demo.holder.tview.time.CountDownTimerView;
import com.demo.holder.biness.time.builder.TimeBuilder;

/**
 * Created by yangjian on 2018/8/25.
 */

public class TimeTestHelper extends BaseDialogHelper<TimeBuilder> {

    private CountDownTimerView mCountDownTimerView;
    public TimeTestHelper(Context context) {
        super(context);
    }

    @Override
    public View onCreateContextView(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.test_dialog_time,null);
        mCountDownTimerView = view.findViewById(R.id.CountDownTimerView);
        return view;
    }

    @Override
    public void setBuilder(TimeBuilder builder, Dialog dialog) {
        super.setBuilder(builder, dialog);
        mCountDownTimerView.setTime(builder.getTime());
        mCountDownTimerView.startCountDown();
    }
}
