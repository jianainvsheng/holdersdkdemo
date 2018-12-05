package com.demo.holder.common.ui.empty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import java.util.Locale;

import cn.tegele.com.common.R;

/**
 * 空数据无网络以及数据加载失败的公共类 说明：调用的类需要 new 然后实现OnEmptyClickListener这个接口
 *
 * @author niuzhikui
 */
public class EmptyViewBox implements OnClickListener, EmptyViewListener {
    public final static int EMPTY_TYPE_NO_CONN = 0;
    public final static int EMPTY_TYPE_DATA_NULL = 1;
    public final static int EMPTY_TYPE_DATA_FAIL = 2;
    public final static int EMPTY_TYPE_DATA_LOADING = 3;
    public final static int EMPTY_TYPE_DATA_LOCAL_NULL = 4;
    public final static int VIEW_TYPE_COMMON = 1;
    private RelativeLayout mLayoutNonNet;
    private ImageView not_net_iv;
    private Button setting_network_button;
    private TextView not_net_hint_one;
    private TextView not_net_hint_two;
    public Button load_again_button;
    private ProgressBar pb_loading;
    private Context mContext;
    private OnEmptyClickListener onEmptyClickListener;
    private ViewSwitcher mSwitcher;
    private String mHintOne;
    private String mHintTwo;
    private int mTipNoNetIcoRes;
    private int mTipNullIcoRes;
    private int mTipFailedIcoRes;
    private int mEmptyType = EMPTY_TYPE_DATA_LOADING;
    private LayoutInflater mInflater;
    private View mTargetView;
    private int mTargetViewType = -1;
    private String mEmptyButtonText;
    private OnClickListener mEmptyTargetListener;
    private Button empty_target_button;

    public EmptyViewBox(Context context, int viewID) {
        this.mContext = context;
        this.mInflater = ((Activity) mContext).getLayoutInflater();
        this.mTargetView = mInflater.inflate(viewID, null, false);
        mTargetViewType = VIEW_TYPE_COMMON;
    }

    public EmptyViewBox(Context context, View targetView) {
        this.mContext = context;
        this.mTargetView = targetView;
        mTargetViewType = VIEW_TYPE_COMMON;
    }

    /**
     * 设置按钮监听事件
     *
     * @param onEmptyClickListener
     */
    public void setOnEmptyClickListener(OnEmptyClickListener onEmptyClickListener) {
        this.onEmptyClickListener = onEmptyClickListener;
    }

    /**
     * 显示没有数据的布局
     */
    public void setOnEmptyTargetClickListener(String emptyButtonText, OnClickListener onClickListener) {
        this.mEmptyTargetListener = onClickListener;
        this.mEmptyButtonText = emptyButtonText;
    }

    /**
     * 创建View
     *
     * @return
     */
    private RelativeLayout onCreateView() {
        mLayoutNonNet = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.common_emptyview_not_net_layout, null);
        mLayoutNonNet.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        mLayoutNonNet.setGravity(Gravity.CENTER);
        initView();
        return mLayoutNonNet;
    }

    /**
     * 初始化
     */
    private void initView() {
        not_net_iv = (ImageView) mLayoutNonNet.findViewById(R.id.not_net_iv);
        setting_network_button = (Button) mLayoutNonNet.findViewById(R.id.setting_network_button);
        setting_network_button.setOnClickListener(this);
        not_net_hint_one =  (TextView)mLayoutNonNet.findViewById(R.id.not_net_hint_one);
        not_net_hint_two = (TextView) mLayoutNonNet.findViewById(R.id.not_net_hint_two);
        not_net_hint_two.setGravity(Gravity.CENTER);// 居中
        load_again_button = (Button) mLayoutNonNet.findViewById(R.id.load_again_button);
        load_again_button.setOnClickListener(this);
        pb_loading = (ProgressBar) mLayoutNonNet.findViewById(R.id.pb_loading);
        empty_target_button = (Button) mLayoutNonNet.findViewById(R.id.empty_target_button);
    }

    /**
     * 显示数据加载失败的
     */
    @Override
    public void showLoadFailedLayout() {
        this.mEmptyType = EMPTY_TYPE_DATA_FAIL;
        updateView(null);
    }

    /**
     * 显示没有网络的
     */
    @Override
    public void showNoNetConnLayout() {
        this.mEmptyType = EMPTY_TYPE_NO_CONN;
        updateView(null);
    }

    public void setHintImageMarginTop(int marginTop) {
        if (marginTop >= 0) {
            if (not_net_iv != null) {
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lp.topMargin = marginTop;
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                not_net_iv.setLayoutParams(lp);
            }
        }

    }

    private int layoutHeight = -1;

    public void setEmptyViewHeight(int height) {
        layoutHeight = height;
    }

    /**
     * 显示没有数据的布局
     */
    public void showNullDataLayout() {
        showNullDataLayout(null);
    }

    /**
     * 显示没有数据的布局
     *
     * @param msg
     */
    public void showNullDataLayout(String msg) {
        this.mEmptyType = EMPTY_TYPE_DATA_NULL;
        updateView(msg);
    }

    /**
     * 显示没有定位
     *
     */
    public void showNullLocalLayout() {
        this.mEmptyType = EMPTY_TYPE_DATA_LOCAL_NULL;
        updateView(null);
    }

    /**
     * 显示数据加载中
     */
    public void showDataLoadingLayout() {
        this.mEmptyType = EMPTY_TYPE_DATA_LOADING;
        updateView(null);
    }

    /**
     * 更新view
     *
     * @param msg 提示
     */
    private void updateView(String msg) {
        if (mLayoutNonNet == null) {
            mLayoutNonNet = onCreateView();
            if (mTargetViewType == VIEW_TYPE_COMMON) {
                ViewGroup group = (ViewGroup) mTargetView.getParent();
                int index = 0;
                Clonner target = new Clonner(mTargetView);
                mSwitcher = new ViewSwitcher(mContext);
                if (group != null) {
                    //判断是否是RelativeLayout
                    String type = group.getClass().getName().substring(group.getClass().getName().lastIndexOf('.') + 1)
                            .toLowerCase(Locale.getDefault());
//                    if ("relativelayout".equals(type) && group.getChildCount() > 1) {
                    if (RelativeLayout.class.isAssignableFrom(group.getClass()) && group.getChildCount() > 1) {
                        //获取自己的
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mTargetView.getLayoutParams();
                        if (layoutHeight > 0) {
                            params.height = layoutHeight;
                        }
                        mSwitcher.setLayoutParams(params);
                        mSwitcher.setId(mTargetView.getId());

                    } else {
                        ViewSwitcher.LayoutParams params = new ViewSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                        if (layoutHeight > 0) {
                            params.height = layoutHeight;
                        }
                        mSwitcher.setLayoutParams(params);

                    }
                    index = group.indexOfChild(mTargetView);
                    group.removeView(mTargetView);

                } else {
                    ViewSwitcher.LayoutParams params = new ViewSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                    if (layoutHeight > 0) {
                        params.height = layoutHeight;
                    }
                    mSwitcher.setLayoutParams(params);

                }
                mSwitcher.addView(mLayoutNonNet, 0);
                //清空原ID
                View view = target.getmView();
                view.setId(View.NO_ID);
                mSwitcher.addView(target.getmView(), 1);
                mSwitcher.setDisplayedChild(1);

                if (group != null) {
                    group.addView(mSwitcher, index);
                } else {
                    ((Activity) mContext).setContentView(mSwitcher);
                }
            }
        }
        if (mSwitcher != null && mSwitcher.getDisplayedChild() != 0) {
            mSwitcher.setDisplayedChild(0);
        }
        switchView(msg);

    }

    /**
     * 隐藏空布局
     */
    @Override
    public void hideAll() {
        if (mSwitcher != null) {
            mSwitcher.setDisplayedChild(1);
        }
    }

    /**
     * @param msg
     * @Description: 切换布局
     * @author niuzhikui
     * @date 2014-12-24 下午1:45:54
     */
    private void switchView(String msg) {
        setDefaultValue();
        if (!TextUtils.isEmpty(msg)) {
            mHintTwo = msg;
        }
        not_net_hint_one.setVisibility(View.GONE);
        switch (mEmptyType) {
            case EMPTY_TYPE_NO_CONN:
//                not_net_iv.setBackgroundResource(mTipNoNetIcoRes);
//                not_net_iv.setVisibility(View.VISIBLE);
//                setting_network_button.setVisibility(View.VISIBLE);
//                not_net_hint_two.setVisibility(View.VISIBLE);//
//                not_net_hint_two.setText(mHintTwo);
//                load_again_button.setVisibility(View.VISIBLE);
//                empty_target_button.setVisibility(View.GONE);
                not_net_iv.setBackgroundResource(R.drawable.emptyview_no_net);
                not_net_iv.setVisibility(View.VISIBLE);
                setting_network_button.setVisibility(View.GONE);
                load_again_button.setVisibility(View.GONE);
                not_net_hint_one.setVisibility(View.VISIBLE);
                not_net_hint_one.setText("网络不见了");
                not_net_hint_two.setVisibility(View.VISIBLE);
                not_net_hint_two.setText("请检查网络连接后再次尝试");
                empty_target_button.setVisibility(View.GONE);
                break;
            // 数据为空
            case EMPTY_TYPE_DATA_NULL:
                not_net_iv.setBackgroundResource(R.drawable.activity_no_data);
                not_net_iv.setVisibility(View.VISIBLE);
                setting_network_button.setVisibility(View.GONE);
                load_again_button.setVisibility(View.GONE);
                not_net_hint_one.setVisibility(View.VISIBLE);
                not_net_hint_one.setText(TextUtils.isEmpty(mHintTwo) ? "暂无达人数据":mHintTwo);
//                not_net_hint_one.setText("暂无达人数据");
                not_net_hint_two.setVisibility(View.GONE);
                if (mEmptyTargetListener != null) {
                    empty_target_button.setVisibility(View.VISIBLE);
                    empty_target_button.setText(mEmptyButtonText);
                    empty_target_button.setOnClickListener(mEmptyTargetListener);
                } else {
                    empty_target_button.setVisibility(View.GONE);
                }

                break;
            // 数据加载失败
            case EMPTY_TYPE_DATA_FAIL:
                not_net_iv.setBackgroundResource(mTipFailedIcoRes);
                not_net_iv.setVisibility(View.VISIBLE);
                setting_network_button.setVisibility(View.GONE);
                load_again_button.setVisibility(View.VISIBLE);
                not_net_hint_two.setVisibility(View.VISIBLE);
                not_net_hint_two.setText(mHintTwo);
                empty_target_button.setVisibility(View.GONE);
                break;
            case EMPTY_TYPE_DATA_LOADING:
                not_net_iv.setVisibility(View.GONE);
                setting_network_button.setVisibility(View.GONE);
                load_again_button.setVisibility(View.GONE);
                not_net_hint_two.setVisibility(View.VISIBLE);
                not_net_hint_two.setText(mHintTwo);
                pb_loading.setVisibility(View.VISIBLE);
                empty_target_button.setVisibility(View.GONE);
                break;
            case EMPTY_TYPE_DATA_LOCAL_NULL:
                not_net_iv.setBackgroundResource(R.drawable.activity_no_local);
                not_net_iv.setVisibility(View.VISIBLE);
                setting_network_button.setVisibility(View.GONE);
                load_again_button.setVisibility(View.GONE);
                not_net_hint_one.setVisibility(View.VISIBLE);
                not_net_hint_one.setText("该页无法显示");
                not_net_hint_two.setVisibility(View.VISIBLE);
                not_net_hint_two.setText("请打开定位服务后再次尝试");
                if (mEmptyTargetListener != null) {
                    empty_target_button.setVisibility(View.VISIBLE);
                    empty_target_button.setText(mEmptyButtonText);
                    empty_target_button.setOnClickListener(mEmptyTargetListener);
                } else {
                    empty_target_button.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 设置默认属性
     */
    private void setDefaultValue() {
        switch (mEmptyType) {
            case EMPTY_TYPE_NO_CONN:
//                if (mTipNoNetIcoRes == 0)
//                    mTipNoNetIcoRes = R.drawable.emptyview_data_loading_error;
//                mHintOne = mContext.getString(R.string.emptyview_not_connect_net);
//                mHintTwo = mContext.getString(R.string.emptyview_not_connect_net_sorry);
                if (mTipNullIcoRes == 0)
//                    mTipNullIcoRes = R.drawable.emptyview_data_null;
                    mTipNullIcoRes = R.drawable.emptyview_no_net;
                mHintOne = "网页看不见了";
                mHintTwo = "请检查网络后再次尝试";
                break;
            // 数据为空
            case EMPTY_TYPE_DATA_NULL:
//                if (mTipNullIcoRes == 0)
////                    mTipNullIcoRes = R.drawable.emptyview_data_null;
//                    mTipNullIcoRes = R.drawable.emptyview_no_net;
//                mHintOne = "网页看不见了";
//                mHintTwo = "请检查网络后再次尝试";
                if (mTipNullIcoRes == 0)
//                    mTipNullIcoRes = R.drawable.emptyview_data_null;
                    mTipNullIcoRes = R.drawable.activity_no_data;
                mHintOne = "暂无达人数据";
                break;

            case EMPTY_TYPE_DATA_LOCAL_NULL:
                if (mTipNullIcoRes == 0)
//                    mTipNullIcoRes = R.drawable.emptyview_data_null;
                    mTipNullIcoRes = R.drawable.activity_no_local;
                mHintOne = "该页无法显示";
                mHintTwo = "请打开定位服务后再次尝试";
                break;
            // 数据加载失败
            case EMPTY_TYPE_DATA_FAIL:
                if (mTipFailedIcoRes == 0)
                    mTipFailedIcoRes = R.drawable.emptyview_data_loading_error;
                mHintTwo = mContext.getString(R.string.emptyview_load_data_err);
                break;
            case EMPTY_TYPE_DATA_LOADING:
                mHintTwo = mContext.getString(R.string.emptyview_load_data_ing);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.setting_network_button) {
            ((Activity) mContext).startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
        } else if (id == R.id.load_again_button) {
            if (onEmptyClickListener != null) {
                onEmptyClickListener.reload(v);
            }
        }
    }

    public String getmHintOne() {
        return mHintOne;
    }

    public void setmHintOne(String mHintOne) {
        this.mHintOne = mHintOne;
    }

    public String getmHintTwo() {
        return mHintTwo;
    }

    public void setmHintTwo(String mHintTwo) {
        this.mHintTwo = mHintTwo;
    }

    public int getmTipNoNetIcoRes() {
        return mTipNoNetIcoRes;
    }

    public void setmTipNoNetIcoRes(int mTipNoNetIcoRes) {
        this.mTipNoNetIcoRes = mTipNoNetIcoRes;
    }

    public int getmTipNullIcoRes() {
        return mTipNullIcoRes;
    }

    public void setmTipNullIcoRes(int mTipNullIcoRes) {
        this.mTipNullIcoRes = mTipNullIcoRes;
    }

    public int getmTipFailedIcoRes() {
        return mTipFailedIcoRes;
    }

    public void setmTipFailedIcoRes(int mTipFailedIcoRes) {
        this.mTipFailedIcoRes = mTipFailedIcoRes;
    }

    public ImageView getNot_net_iv() {
        return not_net_iv;
    }

    public void setNot_net_iv(ImageView not_net_iv) {
        this.not_net_iv = not_net_iv;
    }

    public TextView getNot_net_hint_two() {
        return not_net_hint_two;
    }

    public void setNot_net_hint_two(TextView not_net_hint_two) {
        this.not_net_hint_two = not_net_hint_two;
    }

    public ViewSwitcher getSwitcher() {
        return mSwitcher;
    }

    public ProgressBar getPb_loading() {
        return pb_loading;
    }

    public void setPb_loading(ProgressBar pb_loading) {
        this.pb_loading = pb_loading;
    }


    /**
     * 刷新的接口
     *
     * @author niuzhikui
     */
    public interface OnEmptyClickListener {
        public void reload(View view);
    }

    private class Clonner {
        private View mView;

        public Clonner(View view) {
            this.setmView(view);
        }

        public View getmView() {
            return mView;
        }

        public void setmView(View mView) {
            this.mView = mView;
        }
    }

    /**
     * 拿到emptyview的根布局
     *
     * @return
     */
    public View getRootView() {
        return (mLayoutNonNet == null) ? null : mLayoutNonNet;
    }


}
