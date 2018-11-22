package com.duoduo.main.classify.home.view;

import android.content.Context;

import com.duoduo.commonbase.adapter.QuickHeaderFooterRecyclerViewAdapter;
import com.duoduo.main.base.data.ProductInfoEntity;
import com.duoduo.main.classify.home.data.ClassifySubHomeDataBean;

/**
 * 分类首页adapter
 */
public class ClassifySubHomeAdapter extends QuickHeaderFooterRecyclerViewAdapter<ProductInfoEntity> {


    public ClassifySubHomeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewLayoutId(int viewType) {
        return 0;
    }

    @Override
    public void bindViewHolder(QuickerViewHolder holder, ProductInfoEntity itemData, int position) {

    }

}