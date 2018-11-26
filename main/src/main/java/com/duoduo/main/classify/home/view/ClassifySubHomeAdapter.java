package com.duoduo.main.classify.home.view;

import android.content.Context;

import com.duoduo.commonbase.adapter.QuickHeaderFooterRecyclerViewAdapter;
import com.duoduo.main.R;
import com.duoduo.main.base.data.ProductInfoEntity;

/**
 * 分类首页adapter
 */
public class ClassifySubHomeAdapter extends QuickHeaderFooterRecyclerViewAdapter<ProductInfoEntity> {


    public ClassifySubHomeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.main_common_two_list_item_layout;
    }

    @Override
    public void bindViewHolder(QuickerViewHolder holder, ProductInfoEntity itemData, int position) {

    }

}