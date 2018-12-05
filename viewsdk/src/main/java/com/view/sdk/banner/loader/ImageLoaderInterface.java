package com.view.sdk.banner.loader;

import android.content.Context;
import android.support.annotation.Keep;
import android.view.View;

import java.io.Serializable;

@Keep
public interface ImageLoaderInterface<T extends View> extends Serializable {

    void displayImage(Context context, Object path, T imageView);

    T createImageView(Context context);
}
