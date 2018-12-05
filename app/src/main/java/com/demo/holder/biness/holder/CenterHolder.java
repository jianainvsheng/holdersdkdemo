package com.demo.holder.biness.holder;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.demo.holder.R;
import com.holder.sdk.eventmanger.internal.event.BaseEvent;
import com.holder.sdk.holdermanger.holder.BaseSubscriberHolder;
import com.holder.sdk.holdermanger.holder.IHolderManager;

import java.util.List;
import com.demo.holder.tview.tablayout.TabLayout;


/**
 * Created by yangjian on 2018/8/22.
 */

public class CenterHolder extends BaseSubscriberHolder<List<String>> implements TabLayout.OnSelectedCallBack{


    private TabLayout mTabLayout;

    public static int TYPE_REFURE = 0;

    private List<String> mListData ;

    public CenterHolder(View contentView, IHolderManager manager) {
        super(contentView, manager);
        mTabLayout = contentView.findViewById(R.id.guide_orderlist_tablayout);
    }

    @Override
    public void builder(BaseEvent event, List<String> data) {

    }

    @Override
    public void onMessageEvent(BaseEvent event) {
        super.onMessageEvent(event);
        if(event.getEventType() == TYPE_REFURE){
            if(event.getData() == null){
                return;
            }
            mListData = (List<String>) event.getData();
            if(mListData == null || mListData.size() <= 0){
                return;
            }
            mTabLayout.clearChildView();
            int position = 0;
            for(String bean : mListData){
                mTabLayout.addTab(position , bean);
                position ++ ;
            }
            mTabLayout.initStrip(0);
            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            mTabLayout.setMinSize(dm.widthPixels);
            mTabLayout.setOnSelectedCallBack(this);
            selected(mTabLayout,0);
        }
    }

    @Override
    public void selected(TabLayout tabLayout,int position) {
        mTabLayout.selectTab(position,0);

        if(mListData == null || mListData.size() <= 0){
            return;
        }
        BaseEvent.builder(getContext()).setData(mListData.get(position)).sendEvent(getContext(),TopHolder.class);
    }
}
