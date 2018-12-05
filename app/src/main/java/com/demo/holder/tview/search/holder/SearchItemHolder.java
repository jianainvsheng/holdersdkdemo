package com.demo.holder.tview.search.holder;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.demo.holder.R;
import com.demo.holder.common.ui.array.holder.BaseArrayHolder;
import com.demo.holder.tview.search.model.SearchModel;
import com.demo.holder.tview.search.adapter.SearchLayoutAdapter;


/**
 * Created by yangjian on 2018/8/6.
 */

public class SearchItemHolder extends BaseArrayHolder<SearchModel> implements View.OnClickListener{

    private TextView mTextView;

    private SearchLayoutAdapter mAdapter;

    public SearchItemHolder(final View view, SearchLayoutAdapter adapter) {
        super(view);
        mTextView = (TextView) view.findViewById(R.id.search_item);
    //    mDeleteView = view.findViewById(R.id.search_delete);
        view.setOnClickListener(this);
        this.mAdapter = adapter;
//        this.mAdapter.getTargetLayout().post(new Runnable() {
//            @Override
//            public void run() {
//                mTextView.setMaxWidth((int)(
//                        mAdapter.getTargetLayout().getMeasuredWidth() -
//                                mAdapter.getTargetLayout().getChildAt(0).getRight() -
//                                view.getContext().getResources().getDimension(R.dimen.margin_10dp)-
//                                view.getContext().getResources().getDimension(R.dimen.margin_10dp)-
//                                view.getContext().getResources().getDimension(R.dimen.margin_10dp))/ 2);
//            }
//        });
    }

    @Override
    public void setData(@Nullable SearchModel data) {
        super.setData(data);
        if(TextUtils.isEmpty(data.mText)){
            getContentView().setVisibility(View.GONE);
            mTextView.setVisibility(View.GONE);
        }else{
            getContentView().setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText(data.mText);
        }

    }

    @Override
    public void onClick(View v) {
        if(mAdapter != null){
            this.mAdapter.onDeleteView(this);
        }
    }
}
