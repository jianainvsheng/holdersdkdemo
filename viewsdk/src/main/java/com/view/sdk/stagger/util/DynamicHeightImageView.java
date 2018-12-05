package com.view.sdk.stagger.util;

import android.content.Context;
import android.support.annotation.Keep;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.view.sdk.roundedimageview.RoundedImageView;

/**
 * An {@link ImageView} layout that maintains a consistent width to height aspect ratio.
 */
@Keep
public class DynamicHeightImageView extends RoundedImageView {

    private double mHeightRatio;

    public DynamicHeightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicHeightImageView(Context context) {
        super(context);
    }

    public void setHeightRatio(double ratio) {
        if (ratio != mHeightRatio) {
            mHeightRatio = ratio;
            requestLayout();
        }
    }

    public double getHeightRatio() {
        return mHeightRatio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mHeightRatio > 0.0) {
            // set the image views size
            int width = View.MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width * mHeightRatio);
            setMeasuredDimension(width, height);
        }
        else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
