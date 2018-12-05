package com.demo.holder.biness.slider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.holder.R;
import com.holder.sdk.holder.adapter.BaseListViewAdapter;
import com.demo.holder.tview.slidelayout.SlideLayout;
import com.demo.holder.biness.slider.bean.GuideProductItemBean;
import com.demo.holder.biness.slider.holder.GuideProductHolder;

/**
 * @author Created by yangjian-ds3 on 2018/4/12.
 */

public class GuideProductAdapter extends BaseListViewAdapter<GuideProductItemBean,GuideProductHolder> implements SlideLayout.OnStateChangeListener {

    private SlideLayout mSlideLayout;

    private Class<?> mTargetClass;

    public GuideProductAdapter(Class<?> targetClass){

        this.mTargetClass = targetClass;
    }

    @Override
    public GuideProductHolder onCreateViewHolder(ViewGroup parent) {

        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.test_list_item,parent,false);
        GuideProductHolder holder = new GuideProductHolder(itemView,this,mTargetClass);
        return holder;
    }

    @Override
    public void onBindViewHolder(GuideProductHolder holder, int position) {
        holder.setData(getData().get(position));
    }

    @Override
    public void onItemClickPosition(int position, GuideProductHolder holder) {
        super.onItemClickPosition(position, holder);

    }

    @Override
    public void onClose(SlideLayout layout) {
        mSlideLayout = null;
    }
    @Override
    public void onDown(SlideLayout layout) {
        if (mSlideLayout != null && layout != mSlideLayout){
            mSlideLayout.closeMenu();
        }
    }

    @Override
    public void onOpen(SlideLayout layout) {
        mSlideLayout = layout;
    }

    public void closeMenu(){

        if(mSlideLayout != null){
            mSlideLayout.closeMenu();
        }
    }
}
