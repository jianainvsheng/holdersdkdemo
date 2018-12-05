package com.view.sdk.banner.loader;

import android.content.Context;
import android.support.annotation.Keep;
import android.widget.ImageView;

@Keep
public abstract class ImageLoader implements ImageLoaderInterface<ImageView> {

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }

}
