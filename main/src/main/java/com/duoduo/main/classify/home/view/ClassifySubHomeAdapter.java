package com.duoduo.main.classify.home.view;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duoduo.commonbase.adapter.QuickHeaderFooterRecyclerViewAdapter;
import com.duoduo.main.R;
import com.duoduo.main.base.data.ProductDataUtils;
import com.duoduo.main.base.data.ProductInfoEntity;
import com.duoduo.main.base.data.TopicTwoProductListEntity;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * 分类首页adapter
 */
public class ClassifySubHomeAdapter extends QuickHeaderFooterRecyclerViewAdapter<TopicTwoProductListEntity> {

    private RequestOptions requestOptions = RequestOptions
            .diskCacheStrategyOf(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.main_common_list_item_single_product_item_default_img);

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
        Glide.with(context).clear(itemImg);
        Glide.with(context)
                .load(entity.getImg())
                .apply(requestOptions)
                .into(itemImg);
        TextView itemTitle = (TextView) holder.getView(samllItem, R.id.item_title);
        itemTitle.setText(entity.getTitle());
        TextView itemCoupon = (TextView) holder.getView(samllItem, R.id.item_tag);
        List<ProductInfoEntity.TagListEntity> tags = entity.getTagList();
        if (tags != null && !tags.isEmpty()) {
            itemCoupon.setVisibility(View.VISIBLE);
            ProductInfoEntity.TagListEntity tagListEntity = tags.get(0);
            itemCoupon.setText(tagListEntity.getName());
        } else {
            itemCoupon.setVisibility(View.INVISIBLE);
        }
        TextView itemPostal = (TextView) holder.getView(samllItem, R.id.item_postal);
        TextView itemPrice = (TextView) holder.getView(samllItem, R.id.item_price);
        itemPrice.setText(ProductDataUtils.getProductTopicHandPriceString(entity));
        TextView itemOriginalPrice = (TextView) holder.getView(samllItem, R.id.item_original_price);
        itemOriginalPrice.setText(ProductDataUtils.getProductFinalPriceString(entity));
        itemOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中划线
        TextView itemBuyCount = (TextView) holder.getView(samllItem, R.id.item_buy_count);
        itemBuyCount.setText(ProductDataUtils.getProductSellAmountsString(entity));
    }

}