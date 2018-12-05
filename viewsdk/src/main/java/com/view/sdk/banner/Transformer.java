package com.view.sdk.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.view.sdk.banner.transformer.AccordionTransformer;
import com.view.sdk.banner.transformer.BackgroundToForegroundTransformer;
import com.view.sdk.banner.transformer.CubeInTransformer;
import com.view.sdk.banner.transformer.CubeOutTransformer;
import com.view.sdk.banner.transformer.DefaultTransformer;
import com.view.sdk.banner.transformer.DepthPageTransformer;
import com.view.sdk.banner.transformer.FlipHorizontalTransformer;
import com.view.sdk.banner.transformer.FlipVerticalTransformer;
import com.view.sdk.banner.transformer.ForegroundToBackgroundTransformer;
import com.view.sdk.banner.transformer.RotateDownTransformer;
import com.view.sdk.banner.transformer.RotateUpTransformer;
import com.view.sdk.banner.transformer.ScaleInOutTransformer;
import com.view.sdk.banner.transformer.StackTransformer;
import com.view.sdk.banner.transformer.TabletTransformer;
import com.view.sdk.banner.transformer.ZoomInTransformer;
import com.view.sdk.banner.transformer.ZoomOutSlideTransformer;
import com.view.sdk.banner.transformer.ZoomOutTranformer;

public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
