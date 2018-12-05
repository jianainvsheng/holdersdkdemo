package com.view.sdk.stagger.boundary;

import android.support.annotation.Keep;
import android.view.View;
import android.widget.AbsListView;

import com.view.sdk.smartlayout.api.ScrollBoundaryDecider;
import com.view.sdk.stagger.StaggeredGridView;

/**
 * Created by yangjian on 2018/8/28.
 */
@Keep
public class StaggerBoundary implements ScrollBoundaryDecider {


    private StaggeredGridView mGridView;

    public StaggerBoundary(StaggeredGridView gridView){

        this.mGridView = gridView;
    }
    @Override
    public boolean canRefresh(View content) {

        return isTop(mGridView);
    }

    @Override
    public boolean canLoadMore(View content) {
        return isBottom(mGridView);
    }


    private boolean isTop(AbsListView listView){
        View firstView=null;
        if(listView.getCount()==0){
            return true;
        }
        firstView=listView.getChildAt(0);
        if(firstView!=null){
//            if(listView.getFirstVisiblePosition()==0&&firstView.getTop()==(listView.getListPaddingTop() + mGridView.getChildBottomMargin())){
//                return true;
//            }
            if(listView.getFirstVisiblePosition()==0&&firstView.getTop()==(listView.getListPaddingTop())){
                return true;
            }
        }else{
            return true;
        }

        return false;
    }

    private boolean isBottom(AbsListView listView){
        int lastPosition=listView.getLastVisiblePosition();
        int count=listView.getCount();
        int childCount=listView.getChildCount();
        if(childCount <= 0){
            return false;
        }
        View lastVisibaleView=listView.getChildAt(childCount-1);
//        if(childCount==count){
//            return true;
//        }
        if(lastVisibaleView!=null){
            if(lastPosition==count-1&&lastVisibaleView.getBottom()+listView.getListPaddingBottom() + mGridView.getChildBottomMargin()==listView.getHeight()){
                return true;
            }
        }

        if(childCount - 2 >= 0){
            View lastNextVisibaleView=listView.getChildAt(childCount-2);
            if(lastNextVisibaleView!=null){
                if(lastPosition==count-1&&lastNextVisibaleView.getBottom()+listView.getListPaddingBottom() + mGridView.getChildBottomMargin()==listView.getHeight()){
                    return true;
                }
            }
        }
        return false;
    }
}
