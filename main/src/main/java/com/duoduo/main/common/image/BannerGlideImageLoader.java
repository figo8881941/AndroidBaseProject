package com.duoduo.main.common.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Banner图片加载器
 */
public class BannerGlideImageLoader implements ImageLoaderInterface<ImageView> {

    private RequestOptions requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL);

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).apply(requestOptions).into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        return null;
    }

}
