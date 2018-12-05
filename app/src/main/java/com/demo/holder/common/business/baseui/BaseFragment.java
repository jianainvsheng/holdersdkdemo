package com.demo.holder.common.business.baseui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dialog.sdk.dialog.commom.GNormalDialog;
import com.dialog.sdk.dialog.helper.usually.LoadingHelper;

public abstract class BaseFragment extends Fragment {

    private Dialog dialog;

    private static final String TAG = "BaseFragment";
    /**
     * 布局
     */
    public View mRootView;

    protected boolean mIsVisiable;

    /**
     * 上下文对象
     **/
    public Context mContext;
    /**
     * 获取状态栏的高度
     */

    public int mStatusBarHeight = -1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getResource(), container, false);
        onBuildView(mRootView);
        getBundleArg();
        initView();
        setListeners();

        return mRootView;
    }

    public void onBuildView(View view){

    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.isGMClickPv()) {
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        if(this.isGMClickPv()) {
        }

    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(this.isGMClickPv()) {
        }

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(this.isGMClickPv()) {
        }

    }

    public boolean isGMClickPv() {
        return true;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        hideLoadingDialog();

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        hideLoadingDialog();
        super.onDetach();
    }

    protected boolean isRegisterEventBus() {
        return false;
    }


    public abstract void initView();

    /**
     * 获取传递过来的bundle参数
     **/
    public void getBundleArg() {
    }

    public void setListeners() {
    }

    public abstract int getResource();

    /**
     * TextView
     *
     * @param id
     * @return
     */
    public TextView findTextView(int id) {
        return (TextView) mRootView.findViewById(id);
    }

    /**
     * Button
     *
     * @param id
     * @return
     */
    public Button findButton(int id) {
        return (Button) mRootView.findViewById(id);
    }

    /**
     * LinearLayout
     *
     * @param id
     * @return
     */
    public LinearLayout findLinearLayout(int id) {
        return (LinearLayout) mRootView.findViewById(id);
    }

    /**
     * RelativeLayout
     *
     * @param id
     * @return
     */
    public RelativeLayout findRelativeLayout(int id) {
        return (RelativeLayout) mRootView.findViewById(id);
    }

    /**
     * ImageView
     *
     * @param id
     * @return
     */
    public ImageView findImageView(int id) {
        return (ImageView) mRootView.findViewById(id);
    }

    /**
     * RatingBar
     *
     * @param id
     * @return
     */
    public RatingBar findRatingBar(int id) {
        return (RatingBar) mRootView.findViewById(id);
    }


    /**
     * 通过Id获取view
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T findViewByIdHelper(int id) {
        View targetView = null;
        if (null != mRootView) {
            targetView = mRootView.findViewById(id);
        }
        return targetView == null ? null : (T) targetView;
    }

    /**
     * 当前Fragment后退事件处理
     *
     * @return
     */
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * 主线程中调用
     * 显示加载数据对话框
     */
    public void showLoadingDialog() {
        if (isDetached() || getActivity()==null) {
            return;
        }

        if (dialog == null) {
            dialog = GNormalDialog.onCreateBuiler(getContext())
                    .setThemeStyleResId(cn.tegele.com.common.R.style.dialog_style)
                    .setHelperClass(LoadingHelper.class)
                    .build();
            dialog.show();
        } else {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }
    }

    /**
     * 主线程中调用
     * 隐藏加载数据对话框
     */
    public void hideLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public Dialog getDialog() {
        return dialog;
    }
}
