package com.demo.holder;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import com.holder.sdk.annotation.IHolder;
import com.holder.sdk.annotation.internal.IHolderInfo;
import com.holder.sdk.eventmanger.internal.event.BaseEvent;
import java.util.ArrayList;
import java.util.List;
import com.demo.holder.common.business.baseui.view.BaseMvpNormalView;
import com.demo.holder.common.business.baseui.BaseSubscriberActivity;
import com.demo.holder.biness.holder.BottomHolder;
import com.demo.holder.biness.holder.CenterHolder;
import com.demo.holder.biness.holder.TopHolder;
import com.demo.holder.biness.present.WidgetPresent;
import com.demo.holder.biness.slider.bean.GuideProductItemBean;

@IHolder(holders = {

        @IHolderInfo(resId = R.id.product_list_header_view, holderClass = TopHolder.class),
        @IHolderInfo(resId = R.id.product_list_center_view, holderClass = CenterHolder.class),
        @IHolderInfo(resId = R.id.product_list_bottom_view, holderClass = BottomHolder.class)
})
public class MainActivity extends BaseSubscriberActivity<BaseMvpNormalView, WidgetPresent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().request();
    }

    @Override
    public int onCreateLayoutResId() {
        return R.layout.test_activity_widget;
    }

    @Override
    public void onMessageEvent(BaseEvent event) {

        if (event.getFromClass() == BottomHolder.class) {

            if (event.getEventType() == BottomHolder.EVENT_REFRESH_DATA) {
                refrush();

            } else if (event.getEventType() == BottomHolder.EVENT_LOAD_DATA) {
                BaseEvent.builder(this).setData(null).setEventType(BottomHolder.EVENT_LOAD_DATA_OVER).sendEvent(this, BottomHolder.class);
            }
        }
    }

    private void refrush() {
        List<GuideProductItemBean> list = new ArrayList<>();

        GuideProductItemBean bean = new GuideProductItemBean();
        bean.text = "dialog1";
        list.add(bean);

        bean = new GuideProductItemBean();
        bean.text = "dialog2";
        list.add(bean);

        bean = new GuideProductItemBean();
        bean.text = "倒计时";
        list.add(bean);


        for (int i = 0; i < 30; i++) {
            bean = new GuideProductItemBean();
            bean.text = "测试哦" + i;
            list.add(bean);

        }
        BaseEvent.builder(this).
                setData(list).
                setEventType(BottomHolder.EVENT_REFRESH_DATA_FINISH).
                sendEvent(this, BottomHolder.class);
    }

    @Override
    public void showSuccessLayout() {
        super.showSuccessLayout();

        BaseEvent.builder(this).setData("asdasdasdasdasdas").sendEvent(this, TopHolder.class);

        List<String> mCenterData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mCenterData.add("你好dadsadads" + i);
        }
        BaseEvent.builder(this).setData(mCenterData).setEventType(CenterHolder.TYPE_REFURE).sendEvent(this, CenterHolder.class);
        refrush();
    }

    @NonNull
    @Override
    public WidgetPresent createPresenter() {
        return new WidgetPresent();
    }

    @Override
    public void reload(View view) {

    }
}
