package com.demo.holder.common.ui.array.widget.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.demo.holder.common.ui.array.IArray;

/**
 * Created by yangjian on 2018/8/4.
 */

public abstract class BaseArrayLayout extends ViewGroup {

    private IArray mAdapter;

    public BaseArrayLayout(Context context) {
        super(context);
    }

    public BaseArrayLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseArrayLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(IArray adapter){

        this.mAdapter = adapter;
        this.mAdapter.setArrayLayout(this);
        request();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = 0;
        int width = 0;
        if(getChildCount() > 0){
            for(int i = 0 ; i < getChildCount() ; i ++){
                View view = getChildAt(i);
                view.measure(0,0);
                height = height > view.getMeasuredHeight() ? height : view.getMeasuredHeight();
                width = width > view.getMeasuredWidth() ? height : view.getMeasuredWidth();
            }
        }
        setMeasuredDimension(widthMeasureSpec, getDefaultViewSize(height,heightMeasureSpec));
    }

    public int onHeigthAdd(){
        return 0;
    }
    public void request(int... positions){
        removeAllViews();
        if(this.mAdapter != null && this.mAdapter.getItemCount() > 0){
            for(int i = 0 ; i < this.mAdapter.getItemCount() ; i ++){
                final View view = this.mAdapter.getView(this,i);
                addView(view);
                if(isAddView(positions,i)){
                    addItemView(view);
                }
            }
        }
    }

    private boolean isAddView(int[] positions , int position){

        if(positions == null){
            return false;
        }

        for(int pos : positions){

            if(pos == position)
                return true;
        }
        return false;
    }
    public int getDefaultViewSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size + onHeigthAdd();
                break;
            case MeasureSpec.AT_MOST:
                result = size + onHeigthAdd();
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    public void addItemView(View view){

    }
}
