package com.demo.holder.common.ui.array;

import android.view.View;
import android.view.ViewGroup;

import com.demo.holder.common.ui.array.widget.base.BaseArrayLayout;

/**
 * Created by yangjian on 2018/8/3.
 */

public interface IArray {

    /**
     * item count
     * @return
     */
     int getItemCount();

    /**
     * getview
     * @param parentView
     * @param position
     * @return
     */
     View getView(ViewGroup parentView, int position);

    /**
     * set the searchlayout
     * @param view
     */
    void setArrayLayout(BaseArrayLayout view);

}
