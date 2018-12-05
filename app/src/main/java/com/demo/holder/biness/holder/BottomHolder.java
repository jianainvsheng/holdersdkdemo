package com.demo.holder.biness.holder;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demo.holder.R;
import com.holder.sdk.eventmanger.internal.event.BaseEvent;
import com.holder.sdk.holdermanger.holder.BaseSubscriberHolder;
import com.holder.sdk.holdermanger.holder.IHolderManager;
import com.view.sdk.smartlayout.SmartRefreshLayout;
import com.view.sdk.smartlayout.api.RefreshLayout;
import com.view.sdk.smartlayout.listener.OnLoadMoreListener;
import com.view.sdk.smartlayout.listener.OnRefreshListener;

import java.util.List;
import com.demo.holder.biness.slider.GuideProductAdapter;
import com.demo.holder.biness.slider.bean.GuideProductItemBean;

/**
 * Created by yangjian on 2018/8/22.
 */

public class BottomHolder extends BaseSubscriberHolder<List<GuideProductItemBean>> implements OnRefreshListener,OnLoadMoreListener {


    private SmartRefreshLayout mRefreshLayout;

    private ListView mListView;

    private GuideProductAdapter mAdapter;

    /**
     * 刷新数据
     */
    public static final int EVENT_REFRESH_DATA = 0;

    /**
     * 加载数据
     */
    public static final int EVENT_LOAD_DATA = 1;

    /**
     * 刷新完成
     */
    public static final int EVENT_REFRESH_DATA_FINISH = 2;


    /**
     * 加载完所有数据了
     */
    public static final int EVENT_REFRESH_DATA_OVER = 6;

    /**
     * 加载完成数据
     */
    public static final int EVENT_LOAD_DATA_FINISH = 3;

    /**
     * 加载完所有数据了
     */
    public static final int EVENT_LOAD_DATA_OVER = 4;

    /**
     * 加载完成数据
     */
    public static final int EVENT_LOAD_DATA_FAIL = 5;


    /**
     * 清除完数据
     */
    public static final int EVENT_CLEAR_DATA_ALL = 10;


    /**
     * 刷新数据失败
     */
    public static final int EVENT_REFRESH_DATA_FAIL = 11;


    public BottomHolder(View contentView, IHolderManager manager) {
        super(contentView, manager);
        this.mRefreshLayout = contentView.findViewById(R.id.guide_orderlist_refreshLayout);
        this.mListView = contentView.findViewById(R.id.guide_orderlist_listview);
        this.mAdapter = new GuideProductAdapter(getTargetClass());
        this.mListView.setAdapter(mAdapter);
        this.mRefreshLayout.setOnRefreshListener(this);
        this.mRefreshLayout.setOnLoadMoreListener(this);
        this.mRefreshLayout.setEnableRefresh(true);
        this.mRefreshLayout.setEnableOverScrollBounce(false);
        this.mRefreshLayout.setEnableOverScrollDrag(false);
        this.mRefreshLayout.setEnableAutoLoadMore(true);
    }

    @Override
    public void builder(BaseEvent event, List<GuideProductItemBean> data) {

    }

    @Override
    public void onMessageEvent(BaseEvent event) {
        super.onMessageEvent(event);
        if(event.getEventType() == EVENT_REFRESH_DATA_FINISH){
            List<GuideProductItemBean> datas = null;
            if(event.getData() != null){
                datas = (List<GuideProductItemBean>) event.getData();
            }
            stopScrolly(mListView);
            mRefreshLayout.finishRefresh(0);
            mAdapter.setData(datas);
            mListView.setSelection(0);
        }else if (event.getEventType() == EVENT_LOAD_DATA_FINISH){
            List<GuideProductItemBean> datas = null;
            if(event.getData() != null){
                datas = (List<GuideProductItemBean>) event.getData();
            }
            mRefreshLayout.finishLoadMore(true);
            mAdapter.addData(datas);
        }else if (event.getEventType() == EVENT_LOAD_DATA_OVER){
            List<GuideProductItemBean> datas = null;
            if(event.getData() != null){
                datas = (List<GuideProductItemBean>) event.getData();
            }
            mAdapter.addData(datas);
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    public void stopScrolly(ViewGroup viewGroup){

        viewGroup.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        BaseEvent.builder(getContext())
                .setEventType(BottomHolder.EVENT_REFRESH_DATA)
                .setFromClass(this.getClass())
                .sendEvent(getContext(), getTargetClass());
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        stopScrolly(mListView);
        BaseEvent.builder(getContext())
                .setEventType(BottomHolder.EVENT_LOAD_DATA)
                .setFromClass(this.getClass())
                .sendEvent(getContext(), getTargetClass());
    }
}
