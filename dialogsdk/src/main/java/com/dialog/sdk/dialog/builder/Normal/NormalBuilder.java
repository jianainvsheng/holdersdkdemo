package com.dialog.sdk.dialog.builder.Normal;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.view.View;

import com.dialog.sdk.dialog.builder.BaseBuilder;
import com.dialog.sdk.dialog.commom.GNormalDialog;
import com.dialog.sdk.loading.Z_TYPE;

/**
 * Created by yangjian on 2018/3/21.
 */

@Keep
public class NormalBuilder extends BaseBuilder<NormalBuilder> {

    private String title;            // 标题文字
    private String content;          // 正文文字
    private String positiveName;     // 确认按钮文字
    private String negativeName;      // 取消按钮文字
    private boolean cancelable;      // 是否可取消

    private int titleColor;          // 标题文字颜色
    private int contentColor;        // 正文文字颜色
    private int positiveBtnColor;    // 确认按钮文字颜色
    private int negativeBtnColor;    // 取消按钮文字

    private View customView;         // 自定义视图

    private GNormalDialog.PositiveCallBack positiveCallBack;
    private GNormalDialog.NegativeCallBack negativeCallBack;

    private       Z_TYPE                 mLoadingBuilderType = Z_TYPE.ROTATE_CIRCLE;
    private       int                    mLoadingBuilderColor = Color.parseColor("#99FFFFFF");

    private boolean autoDismiss = true;

    public NormalBuilder(Context context) {

        super(context);
    }

    public String getTitle() {
        return title;
    }

    public NormalBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public NormalBuilder setTitle(int titleRes) {
        this.title = getContext().getResources().getString(titleRes);
        return this;
    }

    public String getContent() {
        return content;
    }

    public NormalBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public NormalBuilder setContent(int contentRes) {
        this.content = getContext().getResources().getString(contentRes);
        return this;
    }

    public String getPositiveName() {
        return positiveName;
    }

    public NormalBuilder setPositiveName(String positiveName) {
        this.positiveName = positiveName;
        return this;
    }

    public NormalBuilder setPositiveName(int positiveNameRes) {
        this.positiveName = getContext().getResources().getString(positiveNameRes);
        return this;
    }

    public String getNegativeName() {
        return negativeName;
    }

    public NormalBuilder setNegativeName(String negativeName) {
        this.negativeName = negativeName;
        return this;
    }

    public NormalBuilder setNegativeName(int negativeNameRes) {
        this.negativeName = getContext().getResources().getString(negativeNameRes);
        return this;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public NormalBuilder setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public int getContentColor() {
        return contentColor;
    }

    public NormalBuilder setContentColor(int contentColor) {
        this.contentColor = contentColor;
        return this;
    }

    public int getPositiveBtnColor() {
        return positiveBtnColor;
    }

    public NormalBuilder setPositiveBtnColor(int positiveBtnColor) {
        this.positiveBtnColor = positiveBtnColor;
        return this;
    }

    public int getNegativeBtnColor() {
        return negativeBtnColor;
    }

    public NormalBuilder setNegativeBtnColor(int negativeBtnColor) {
        this.negativeBtnColor = negativeBtnColor;
        return this;
    }

    public View getCustomView() {
        return customView;
    }

    public NormalBuilder setCustomView(View customView) {
        this.customView = customView;
        return this;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public NormalBuilder setCancelable(boolean canclable) {
        this.cancelable = canclable;
        return this;
    }

    public boolean isAutoDismiss() {
        return autoDismiss;
    }

    public NormalBuilder setAutoDismiss(boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
        return this;
    }

    public GNormalDialog.PositiveCallBack getPositiveCallBack() {
        return positiveCallBack;
    }

    public NormalBuilder setPositiveCallBack(GNormalDialog.PositiveCallBack positiveCallBack) {
        this.positiveCallBack = positiveCallBack;
        return this;
    }

    public GNormalDialog.NegativeCallBack getNegativeCallBack() {
        return negativeCallBack;
    }

    public NormalBuilder setNegativeCallBack(GNormalDialog.NegativeCallBack negativeCallBack) {
        this.negativeCallBack = negativeCallBack;
        return this;
    }

    public NormalBuilder setLoadingBuilder(@NonNull Z_TYPE type)
    {
        this.mLoadingBuilderType = type;
        return this;
    }

    public NormalBuilder setLoadingColor(@ColorInt int color)
    {
        this.mLoadingBuilderColor = color;
        return this;
    }

    public int getLoadingBuilderColor() {
        return mLoadingBuilderColor;
    }

    public Z_TYPE getLoadingBuilderType() {
        return mLoadingBuilderType;
    }
}

