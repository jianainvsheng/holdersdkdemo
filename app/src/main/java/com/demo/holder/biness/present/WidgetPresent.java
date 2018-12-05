package com.demo.holder.biness.present;

import com.demo.holder.common.business.baseui.view.BaseMvpNormalView;
import com.demo.holder.common.ui.mvp.MvpBasePresenter;

/**
 * Created by yangjian on 2018/8/21.
 */

public class WidgetPresent extends MvpBasePresenter<BaseMvpNormalView> {

    public void request(){

        if(getView() != null){

            getView().showSuccessLayout();
        }
    }
}
