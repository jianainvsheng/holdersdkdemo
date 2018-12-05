package com.demo.holder.common.business.baseui;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.demo.holder.common.business.baseui.systembar.SystemBarTintManager;
import com.dialog.sdk.dialog.commom.GNormalDialog;
import com.dialog.sdk.dialog.helper.usually.LoadingHelper;


public class BaseActivity extends AppCompatActivity {

    private Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);

    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void finish() {

        super.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }

    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
    }

    /**
     * 显示数据加载dialog
     */
    public void showLoadingDialog() {
        if (!this.isFinishing() && (Build.VERSION.SDK_INT < 17 || !this.isDestroyed())) {
            if (null == this.dialog) {
                dialog = GNormalDialog.onCreateBuiler(this)
                        .setThemeStyleResId(cn.tegele.com.common.R.style.dialog_style)
                        .setHelperClass(LoadingHelper.class)
                        .build();
                dialog.show();
            } else if (!this.dialog.isShowing()) {
                this.dialog.show();
            }

        }

    }


    /**
     * 隐藏数据加载dialog
     */
    public void hideLoadingDialog() {
        if (!this.isFinishing() && (Build.VERSION.SDK_INT < 17 || !this.isDestroyed())) {
            if (this.dialog != null && this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

        }
    }


    public void setSystemBarBg(int id) {

        int color = getResources().getColor(id);

        int traColor = Color.parseColor("#00000000");

        if(color != traColor){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setTranslucentStatus(true);
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                tintManager.setStatusBarTintEnabled(true);
                tintManager.setStatusBarTintResource(id);
            }
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(id);
        }
    }

    public void setSysteFullBarBg(int id) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(id);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public Dialog getDialog() {
        return dialog;
    }
}
