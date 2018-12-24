package com.duoduo.main.classify.view;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duoduo.commonbase.adapter.QuickHeaderFooterRecyclerViewAdapter;
import com.duoduo.commonbase.utils.NumberUtils;
import com.duoduo.main.R;
import com.duoduo.main.base.data.ProductDataUtils;
import com.duoduo.main.base.data.ProductInfoEntity;

import pl.droidsonroids.gif.GifImageView;

/**
 * 排行榜Adapter
 */
public class ClassifyHotSellAdapter extends QuickHeaderFooterRecyclerViewAdapter<ProductInfoEntity> {

    private RequestOptions requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL);

    private String sellCountFormat;

    public ClassifyHotSellAdapter(Context context) {
        super(context);
        Resources resources = this.context.getResources();
        sellCountFormat = resources.getString(R.string.main_classify_module_hot_sell_sell_count_format);
    }

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.main_classify_module_hot_sell_prd_item;
    }

    @Override
    public void bindViewHolder(QuickerViewHolder holder, ProductInfoEntity itemData, int position) {
        GifImageView itemImg = (GifImageView) holder.getView(R.id.item_img);
        Glide.with(context).clear(itemImg);
        Glide.with(context).load(itemData.getImg()).apply(requestOptions).into(itemImg);
        TextView itemPosition = (TextView) holder.getView(R.id.item_position);
        if (position < 3) {
            itemPosition.setVisibility(View.VISIBLE);
            itemPosition.setText(String.valueOf(position + 1));
        } else {
            itemPosition.setVisibility(View.GONE);
        }
        TextView itemName = (TextView) holder.getView(R.id.item_name);
        itemName.setText(itemData.getTitle());
        TextView itemPrice = (TextView) holder.getView(R.id.item_price);
        itemPrice.setText(ProductDataUtils.getProductHandPriceString(itemData));
        TextView itemSellCount = (TextView) holder.getView(R.id.item_sell_count);
        itemSellCount.setText(String.format(sellCountFormat, itemData.getSellAmounts()));
    }
}
