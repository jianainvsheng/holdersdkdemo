package com.dialog.sdk.dialog.commom;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;

import com.dialog.sdk.dialog.base.BaseDialog;
import com.dialog.sdk.dialog.builder.BaseBuilder;
import com.dialog.sdk.dialog.builder.Normal.NormalBuilder;
import com.dialog.sdk.dialog.helper.BaseDialogHelper;
import com.dialog.sdk.dialog.helper.usually.UsuallyDialogHelper;

/**
 * Created by yangjian-ds3 on 2018/3/21.
 */
@Keep
public class GNormalDialog< N extends BaseBuilder<N>> extends BaseDialog<N> {

    public GNormalDialog(@NonNull Context context) {
        super(context);
    }

    public GNormalDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    public BaseDialogHelper onCreateDefaultHelp(Context context, N data) {
        return new UsuallyDialogHelper(context);
    }
    @Keep
    public static NormalBuilder onCreateBuiler(Context context){

        return new NormalBuilder(context);
    }
    @Keep
    public static < N extends BaseBuilder<N>> N onCreateBuiler(N n){

        return n;
    }

    @Keep
    public interface PositiveCallBack {
        void onClick(View v);
    }
    @Keep
    public interface NegativeCallBack {
        void onClick(View v);
    }
}
