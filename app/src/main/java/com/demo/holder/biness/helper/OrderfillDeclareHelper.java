package com.demo.holder.biness.helper;

import android.app.Dialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.dialog.sdk.dialog.builder.Normal.NormalBuilder;
import com.dialog.sdk.dialog.helper.BaseDialogHelper;

import com.demo.holder.R;
import com.demo.holder.biness.uils.ScreenUtils;

/**
 * Created by yangjian-ds3 on 2018/3/21.
 */

public class OrderfillDeclareHelper extends BaseDialogHelper<NormalBuilder> {

    private TextView mMessage;
    private Button mPositiveButton;

    public OrderfillDeclareHelper(Context context) {
        super(context);
    }

    @Override
    public View onCreateContextView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.orderfill_declare_layout, null);
        view.setBackgroundResource(R.drawable.orderfill_declare_bg);
        mMessage = (TextView) view.findViewById(R.id.message);
        mPositiveButton = (Button) view.findViewById(R.id.positiveButton);
        return view;
    }

    @Override
    public void setBuilder(NormalBuilder builder, final Dialog dialog) {
        super.setBuilder(builder, dialog);
        mMessage.setMovementMethod(ScrollingMovementMethod.getInstance());
        mMessage.setText(builder.getContent());
        mPositiveButton.setText(builder.getPositiveName());
        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        final View tempView = getContextView();
        ViewTreeObserver
                vto = tempView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tempView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = tempView.getMeasuredHeight();
                setDialogMaxHeight(height, mMessage);
            }
        });

        Window window = getDialog().getWindow();
        if (null != window) {
            window.setGravity(Gravity.CENTER);
        }

        Window dialogWindow = getDialog().getWindow();
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        params.width = (int) (ScreenUtils.getDisplayWidth(getContext()) * 0.85);
        params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(params);
    }

    private void setDialogMaxHeight(int expectHeight, TextView textView) {
        int maxHeight = (int) (ScreenUtils.getDisplayHeight(getContext()) * 0.6);
        int setHeight = maxHeight > expectHeight ? expectHeight : (int) maxHeight;
        if (null != getDialog().getWindow()) {
            WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
            lp.width = (int) (ScreenUtils.getDisplayWidth(getContext()) * 0.85);
            lp.height = setHeight;
            getDialog().getWindow().setAttributes(lp);
        }
        if (setHeight == maxHeight && null != textView){
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }
}
