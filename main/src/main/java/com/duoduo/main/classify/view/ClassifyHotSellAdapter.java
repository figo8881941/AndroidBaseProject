package com.duoduo.main.classify.view;

import android.content.Context;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duoduo.commonbase.adapter.QuickRecyclerViewAdapter;
import com.duoduo.main.R;
import com.duoduo.main.classify.home.data.ClassifySubHomeDataBean;

import pl.droidsonroids.gif.GifImageView;

/**
 * 排行榜Adapter
 */
public class ClassifyHotSellAdapter extends QuickRecyclerViewAdapter<ClassifySubHomeDataBean.ModuleDtoListEntity.ProductInfoListEntity> {

    private RequestOptions requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL);

    public ClassifyHotSellAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.main_classify_module_hot_sell_prd_item;
    }

    @Override
    public void bindViewHolder(QuickerViewHolder holder, ClassifySubHomeDataBean.ModuleDtoListEntity.ProductInfoListEntity itemData, int position) {
        GifImageView itemImg = (GifImageView) holder.getView(R.id.item_img);
        Glide.with(context).load(itemData.getImg()).apply(requestOptions).into(itemImg);
        TextView itemName = (TextView) holder.getView(R.id.item_name);
        itemName.setText(itemData.getTitle());
        TextView itemPrice = (TextView) holder.getView(R.id.item_price);
        itemPrice.setText("¥" + itemData.getCouponFinalPrice());
        TextView itemSellCount = (TextView) holder.getView(R.id.item_sell_count);
        itemSellCount.setText(itemData.getSellAmounts() + "人已买");

    }
}
