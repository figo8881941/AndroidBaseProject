package com.duoduo.main.glide;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

@GlideExtension
public class MainGlideExtension {

    private MainGlideExtension() {

    }

    @GlideOption
    public static void applyGlobalOptions(RequestOptions options) {
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
    }
}
