package com.holder.sdk.holder.adapter;

import android.support.annotation.Keep;
import android.view.View;
import android.view.ViewGroup;

import com.holder.sdk.holder.BaseHolder;

import cn.tegele.com.common.R;

/**
 * @author Created by yangjian on 2017/12/19.
 */
@Keep
public abstract class BaseListViewTypeAdapter<T, H extends BaseHolder<T>> extends BaseListViewAdapter<T,H> {

    @Override
    public H onCreateViewHolder(ViewGroup parent) {
        return onCreateViewHolder(parent,0);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(getViewTypeCount() <= 1){
            return super.getView(position,convertView,parent);
        }else{
            H holder;
            if (convertView == null) {
                holder = onCreateViewHolder(parent,getItemViewType(position));
                convertView = holder.getContentView();
                convertView.setTag(R.id.common_orderlist_product_detail_list_view_item_view_id, holder);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        H itemHolder = (H) v.getTag(R.id.common_orderlist_product_detail_list_view_item_view_id);
                        onItemClickPosition(position,itemHolder);
                        if (getItemClickListener() != null) {
                            getItemClickListener().onListViewItemClick(position, itemHolder);
                        }
                    }
                });
            } else {
                holder = (H) convertView.getTag(R.id.common_orderlist_product_detail_list_view_item_view_id);
            }
            holder.setPosition(position);
            onBindViewHolder(holder, position);
            return convertView;
        }
    }
    @Override
    public int getItemViewType(int position) {
        return getItemType(position);
    }

    @Override
    public int getViewTypeCount() {
        return getItemCount();
    }


    /**
     * 创建holder
     * @param parent
     * @param viewType
     * @return
     */
    public abstract H onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 得到当前holder的type
     * @param position
     * @return
     */
    public abstract int getItemType(int position);

    /**
     * 获得item的个数
     * @return
     */
    public abstract int getItemCount();
}
