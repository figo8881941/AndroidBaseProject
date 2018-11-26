package com.duoduo.main.classify.home.view;

import android.content.Context;

import com.duoduo.commonbase.adapter.QuickHeaderFooterRecyclerViewAdapter;
import com.duoduo.main.R;
import com.duoduo.main.base.data.TopicTwoProductListEntity;

/**
 * 分类首页adapter
 */
public class ClassifySubHomeAdapter extends QuickHeaderFooterRecyclerViewAdapter<TopicTwoProductListEntity> {


    public ClassifySubHomeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.main_common_list_item_two_product_layout;
    }

    @Override
    public void bindViewHolder(QuickerViewHolder holder, TopicTwoProductListEntity itemData, int position) {

    }

}