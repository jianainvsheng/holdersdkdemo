package com.dialog.sdk.loading;

import android.support.annotation.Keep;

import com.dialog.sdk.loading.ball.ElasticBallBuilder;
import com.dialog.sdk.loading.ball.InfectionBallBuilder;
import com.dialog.sdk.loading.ball.IntertwineBuilder;
import com.dialog.sdk.loading.circle.DoubleCircleBuilder;
import com.dialog.sdk.loading.circle.PacManBuilder;
import com.dialog.sdk.loading.circle.RotateCircleBuilder;
import com.dialog.sdk.loading.circle.SingleCircleBuilder;
import com.dialog.sdk.loading.circle.SnakeCircleBuilder;
import com.dialog.sdk.loading.clock.CircleBuilder;
import com.dialog.sdk.loading.clock.ClockBuilder;
import com.dialog.sdk.loading.path.MusicPathBuilder;
import com.dialog.sdk.loading.path.SearchPathBuilder;
import com.dialog.sdk.loading.path.StairsPathBuilder;
import com.dialog.sdk.loading.rect.ChartRectBuilder;
import com.dialog.sdk.loading.rect.StairsRectBuilder;
import com.dialog.sdk.loading.star.LeafBuilder;
import com.dialog.sdk.loading.star.StarBuilder;
import com.dialog.sdk.loading.text.TextBuilder;

/**
 * Created by zyao89 on 2017/3/19.
 * Contact me at 305161066@qq.com or zyao89@gmail.com
 * For more projects: https://github.com/zyao89
 * My Blog: http://zyao89.me
 */

@Keep
public enum Z_TYPE
{
    CIRCLE(CircleBuilder.class),
    CIRCLE_CLOCK(ClockBuilder.class),
    STAR_LOADING(StarBuilder.class),
    LEAF_ROTATE(LeafBuilder.class),
    DOUBLE_CIRCLE(DoubleCircleBuilder.class),
    PAC_MAN(PacManBuilder.class),
    ELASTIC_BALL(ElasticBallBuilder.class),
    INFECTION_BALL(InfectionBallBuilder.class),
    INTERTWINE(IntertwineBuilder.class),
    TEXT(TextBuilder.class),
    SEARCH_PATH(SearchPathBuilder.class),
    ROTATE_CIRCLE(RotateCircleBuilder.class),
    SINGLE_CIRCLE(SingleCircleBuilder.class),
    SNAKE_CIRCLE(SnakeCircleBuilder.class),
    STAIRS_PATH(StairsPathBuilder.class),
    MUSIC_PATH(MusicPathBuilder.class),
    STAIRS_RECT(StairsRectBuilder.class),
    CHART_RECT(ChartRectBuilder.class),;

    private final Class<?> mBuilderClass;

    Z_TYPE(Class<?> builderClass)
    {
        this.mBuilderClass = builderClass;
    }

    <T extends ZLoadingBuilder> T newInstance()
    {
        try
        {
            return (T) mBuilderClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
