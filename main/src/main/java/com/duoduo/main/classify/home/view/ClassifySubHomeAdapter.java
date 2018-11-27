package com.duoduo.main.classify.home.view;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duoduo.commonbase.adapter.QuickHeaderFooterRecyclerViewAdapter;
import com.duoduo.main.R;
import com.duoduo.main.base.data.ProductInfoEntity;
import com.duoduo.main.base.data.TopicTwoProductListEntity;

import pl.droidsonroids.gif.GifImageView;

/**
 * 分类首页adapter
 */
public class ClassifySubHomeAdapter extends QuickHeaderFooterRecyclerViewAdapter<TopicTwoProductListEntity> {

    private RequestOptions requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL);

    public ClassifySubHomeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.main_common_list_item_two_product_layout;
    }

    @Override
    public void bindViewHolder(QuickerViewHolder holder, TopicTwoProductListEntity itemData, int position) {
        View leftItem = holder.getView(R.id.left_item);
        ProductInfoEntity leftEntity = itemData.getLeftProductInfoEntity();
        initSingleItemWithData(holder, leftItem, leftEntity);

        View rightItem = holder.getView(R.id.right_item);
        ProductInfoEntity rightEntity = itemData.getRightProductInfoEntity();
        if (rightEntity != null) {
            rightItem.setVisibility(View.VISIBLE);
            initSingleItemWithData(holder, rightItem, rightEntity);
        } else {
            rightItem.setVisibility(View.GONE);
        }
    }

    private void initSingleItemWithData(QuickerViewHolder holder, View samllItem, ProductInfoEntity entity) {
        GifImageView itemImg = (GifImageView) holder.getView(samllItem, R.id.item_img);
        Glide.with(context).load(entity.getImg()).apply(requestOptions).into(itemImg);
    }

}