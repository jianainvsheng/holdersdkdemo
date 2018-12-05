package com.demo.holder.biness.slider.holder;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.demo.holder.R;
import com.dialog.sdk.dialog.commom.GNormalDialog;
import com.holder.sdk.holder.BaseHolder;
import com.demo.holder.tview.slidelayout.SlideLayout;
import com.demo.holder.biness.slider.bean.GuideProductItemBean;
import com.demo.holder.biness.helper.OrderfillDeclareHelper;
import com.demo.holder.biness.time.TimeTestHelper;
import com.demo.holder.biness.time.builder.TimeBuilder;

/**
 * @author Created by yangjian-ds3 on 2018/4/12.
 */

public class GuideProductHolder extends BaseHolder<GuideProductItemBean> implements View.OnClickListener{

    private TextView mProductTitle;

    private SlideLayout mSlideLayout;

    public GuideProductHolder(View contentView, SlideLayout.OnStateChangeListener listener, Class<?> targetClass) {
        super(contentView);
        this.mSlideLayout =  contentView.findViewById(R.id.slidelayout_test);
        this.mSlideLayout.setOnStateChangeListener(listener);
        this.mProductTitle =  contentView.findViewById(R.id.title_test);
        mProductTitle.setOnClickListener(this);
    }

    @Override
    public void setData(GuideProductItemBean data) {
        super.setData(data);
        if(!TextUtils.isEmpty(data.text)){
            mProductTitle.setVisibility(View.VISIBLE);
            mProductTitle.setText(data.text);
        }else{
            mProductTitle.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {

        if(getPosition() == 0){

            Dialog dialog = GNormalDialog.onCreateBuiler(getContext())
                    .setContent("大家好我是新版本OrderfillDeclareDialog")
                    .setPositiveName("ok")
                    .setThemeStyleResId(R.style.dialog_style)
                    .setHelperClass(OrderfillDeclareHelper.class)
                    .build();
            dialog.show();
        }else if(getPosition() == 1){

            Dialog dialog = GNormalDialog.onCreateBuiler(getContext())
                    .setContent("你好我是新版本GCommonDialog")
                    .setThemeStyleResId(R.style.dialog_style)
                    .setNegativeName("cancle")
                    .setPositiveName("ok").build();
            dialog.show();
        }else if(getPosition() == 2){

            Dialog dialog = GNormalDialog.onCreateBuiler(new TimeBuilder(getContext()))
                    .setHelperClass(TimeTestHelper.class)
                    .setTime(1000 * 60 * 3)
                    .build();
            dialog.show();
        }
    }
}
