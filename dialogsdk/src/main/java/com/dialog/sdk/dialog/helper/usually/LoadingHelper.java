package com.dialog.sdk.dialog.helper.usually;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.support.annotation.Keep;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import cn.tegele.com.common.R;
import com.dialog.sdk.dialog.builder.Normal.NormalBuilder;
import com.dialog.sdk.dialog.helper.BaseDialogHelper;
import com.dialog.sdk.loading.ZLoadingView;

/**
 * Created by yangjian-ds3 on 2018/3/21.
 */
@Keep
public class LoadingHelper extends BaseDialogHelper<NormalBuilder> implements Dialog.OnDismissListener,DialogInterface.OnShowListener{


    private ZLoadingView mLoading;
    private TextView textView;
    private double  mDurationTimePercent    = 1.0f;

    public LoadingHelper(Context context) {
        super(context);
    }

    @Override
    public View onCreateContextView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_loading_dialog, null);
        mLoading = view.findViewById(R.id.activity_loading);
        textView = view.findViewById(R.id.activity_bookloading_text);
        return view;
    }

    @Override
    public void setBuilder(NormalBuilder builder, final Dialog dialog) {
        super.setBuilder(builder, dialog);
        if(builder.getTitle() != null && builder.getTitle().length() != 0){
            textView.setText(builder.getTitle());
        }
        Window window = getDialog().getWindow();
        if (null != window) {
            window.setGravity(Gravity.CENTER);
        }
//        Window dialogWindow = getDialog().getWindow();
//        WindowManager.LayoutParams params = dialogWindow.getAttributes();
//        params.width = (int) (ScreenUtils.getDisplayWidth(getContext()) * 0.85);
//        params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
//        dialogWindow.setAttributes(params);
        mLoading.setLoadingBuilder(builder.getLoadingBuilderType());
        // 设置间隔百分比
        if (mLoading.getZLoadingBuilder() != null)
        {
            mLoading.getZLoadingBuilder().setDurationTimePercent(this.mDurationTimePercent);
        }
        mLoading.setColorFilter(builder.getLoadingBuilderColor(), PorterDuff.Mode.SRC_ATOP);
        dialog.setOnDismissListener(this);
        dialog.setOnShowListener(this);
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

//        if(mLoading.isStart()){
//            mLoading.stop();
//        }
        mLoading.setVisibility(View.GONE);
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {

//        if(!mLoading.isStart()){
//            mLoading.start();
//        }
        mLoading.setVisibility(View.VISIBLE);
    }
}
