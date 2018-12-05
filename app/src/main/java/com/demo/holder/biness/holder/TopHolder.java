package com.demo.holder.biness.holder;

import android.text.TextUtils;
import android.view.View;

import com.holder.sdk.eventmanger.internal.event.BaseEvent;
import com.holder.sdk.holdermanger.holder.BaseSubscriberHolder;
import com.holder.sdk.holdermanger.holder.IHolderManager;

import com.demo.holder.R;
import com.demo.holder.tview.search.SearchScrollView;
import com.demo.holder.tview.search.adapter.SearchLayoutAdapter;

/**
 * Created by yangjian on 2018/8/22.
 */

public class TopHolder extends BaseSubscriberHolder<String> {


    public SearchScrollView mSearchLayout;

    private SearchLayoutAdapter mAdapter;

    public TopHolder(View contentView, IHolderManager manager) {
        super(contentView, manager);
        mSearchLayout = contentView.findViewById(R.id.home_top_searchlayout);
        mAdapter = new SearchLayoutAdapter();
        mSearchLayout.setAdapter(mAdapter);
    }

    @Override
    public void builder(BaseEvent event, String data) {

        if(!TextUtils.isEmpty(data)){
            mAdapter.addData(data);
        }
    }

    @Override
    public void onMessageEvent(BaseEvent event) {
        super.onMessageEvent(event);
    }
}
