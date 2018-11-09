package com.duoduo.main.classify.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.duoduo.main.R;
import com.duoduo.main.classify.consts.IClassifyConsts;
import com.duoduo.main.classify.home.data.ClassifySubHomeDataBean;
import com.duoduo.main.common.image.BannerGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * 分类首页ViewHelper
 */
public class ClassifyViewHelper {

    private static RequestOptions requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL);

    /**
     * 数据初始化headerView的方法
     *
     * @param context
     * @param dataBean
     */
    public static View createHeaderViewByData(Context context, ClassifySubHomeDataBean dataBean) {
        if (context == null || dataBean == null) {
            return null;
        }
        List<ClassifySubHomeDataBean.ModuleDtoListEntity> moduleDtoListEntities = dataBean.getModuleDtoList();
        if (moduleDtoListEntities == null || moduleDtoListEntities.isEmpty()) {
            return null;
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (ClassifySubHomeDataBean.ModuleDtoListEntity entity : moduleDtoListEntities) {
            createModuleView(context, linearLayout, entity);
        }
        return linearLayout;
    }

    /**
     * 根据数据创建模块
     *
     * @param context
     * @param parent
     * @param entity
     * @return
     */
    public static void createModuleView(Context context, LinearLayout parent, ClassifySubHomeDataBean.ModuleDtoListEntity entity) {
        if (context == null || parent == null || entity == null) {
            return;
        }
        int moduleType = entity.getType();
        switch (moduleType) {
            case IClassifyConsts
                    .ModuleType.BANNER_LAYGE_750_270: {
                createBannerLayge750_270(context, parent, entity);
            }
            break;
            case IClassifyConsts
                    .ModuleType.COMMON_GRID_THREE: {
                createGridThree(context, parent, entity);
            }
            break;
            case IClassifyConsts
                    .ModuleType.BANNER_SMALL: {
                createBannerSmall(context, parent, entity);
            }
            break;
            case IClassifyConsts
                    .ModuleType.HOT_SELL: {
                createHotSell(context, parent, entity);
            }
            break;
        }
    }

    /**
     * 根据数据创建小Banner模块
     *
     * @param context
     * @param parent
     * @param entity
     */
    private static void createBannerSmall(Context context, LinearLayout parent, ClassifySubHomeDataBean.ModuleDtoListEntity entity) {
        if (context == null || parent == null || entity == null) {
            return;
        }

        List<ClassifySubHomeDataBean.ModuleDtoListEntity.EntranceItemDtoListEntity> entranceItemDtoListEntities = entity.getEntranceItemDtoList();

        if (entranceItemDtoListEntities == null || entranceItemDtoListEntities.isEmpty()) {
            return;
        }
        ViewGroup bannerSmall = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.main_classify_module_banner_small, parent, false);
        //调整布局
        adjustModuleLayout(context, parent, bannerSmall);
        parent.addView(bannerSmall);

        ClassifySubHomeDataBean.ModuleDtoListEntity.EntranceItemDtoListEntity entranceItemDtoListEntity = entranceItemDtoListEntities.get(0);
        GifImageView bannerImg = (GifImageView) bannerSmall.findViewById(R.id.banner_img);
        Glide.with(context).load(entranceItemDtoListEntity.getImg()).apply(requestOptions).into(bannerImg);
    }

    /**
     * 根据数据创建排行榜模块
     *
     * @param context
     * @param parent
     * @param entity
     */
    private static void createHotSell(Context context, LinearLayout parent, ClassifySubHomeDataBean.ModuleDtoListEntity entity) {
        if (context == null || parent == null || entity == null) {
            return;
        }

        List<ClassifySubHomeDataBean.ModuleDtoListEntity.ProductInfoListEntity> productInfoListEntities = entity.getProductInfoList();

        if (productInfoListEntities == null || productInfoListEntities.isEmpty()) {
            return;
        }
        ViewGroup hotSell = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.main_classify_module_hot_sell, parent, false);
        //调整布局
        adjustModuleLayout(context, parent, hotSell);
        parent.addView(hotSell);

        //标题
        TextView titleText = (TextView) hotSell.findViewById(R.id.title_text);
        GifImageView titleImg = (GifImageView) hotSell.findViewById(R.id.title_img);
        String titleString = entity.getTitle();
        String titleImgUrl = entity.getTitleImg();
        if (!TextUtils.isEmpty(titleImgUrl)) {
            titleText.setVisibility(View.GONE);
            titleImg.setVisibility(View.VISIBLE);
            Glide.with(context).load(titleImgUrl).apply(requestOptions).into(titleImg);
        } else {
            titleText.setVisibility(View.VISIBLE);
            titleImg.setVisibility(View.GONE);
            titleText.setText(titleString);
        }

        //ViewPager
        RecyclerView itemRecyclerView = (RecyclerView) hotSell.findViewById(R.id.item_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        itemRecyclerView.setLayoutManager(linearLayoutManager);
        ClassifyHotSellAdapter classifyHotSellAdapter = new ClassifyHotSellAdapter(context);
        classifyHotSellAdapter.setData(productInfoListEntities);
        View footerView = LayoutInflater.from(context).inflate(R.layout.main_classify_module_hot_sell_seemore_item, itemRecyclerView, false);
        classifyHotSellAdapter.setFooterView(footerView);
        itemRecyclerView.setAdapter(classifyHotSellAdapter);
    }

    /**
     * 根据数据创建网格模块
     *
     * @param context
     * @param parent
     * @param entity
     */
    private static void createGridThree(Context context, LinearLayout parent, ClassifySubHomeDataBean.ModuleDtoListEntity entity) {
        if (context == null || parent == null || entity == null) {
            return;
        }

        List<ClassifySubHomeDataBean.ModuleDtoListEntity.EntranceItemDtoListEntity> entranceItemDtoListEntities = entity.getEntranceItemDtoList();

        if (entranceItemDtoListEntities == null || entranceItemDtoListEntities.isEmpty()) {
            return;
        }

        ViewGroup gridThree = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.main_classify_module_grid_three, parent, false);
        //调整布局
        adjustModuleLayout(context, parent, gridThree);
        parent.addView(gridThree);

        int size = entranceItemDtoListEntities.size();
        ClassifySubHomeDataBean.ModuleDtoListEntity.EntranceItemDtoListEntity entranceItemDtoListEntity = entranceItemDtoListEntities.get(0);

        //第一个项
        ViewGroup item = (ViewGroup) gridThree.findViewById(R.id.item_one);
        TextView itemTile = (TextView) item.findViewById(R.id.item_one_title);
        TextView itemDesc = (TextView) item.findViewById(R.id.item_one_desc);
        TextView itemTag = (TextView) item.findViewById(R.id.item_one_tag);
        GifImageView itemImg = (GifImageView) item.findViewById(R.id.item_one_img);
        GifImageView itemGifImg = (GifImageView) item.findViewById(R.id.item_one_gif_img);
        initGridThreeItem(context, entranceItemDtoListEntity, itemTile, itemDesc, itemTag, itemImg, itemGifImg);

        //第二项
        if (size >= 2) {
            entranceItemDtoListEntity = entranceItemDtoListEntities.get(1);
            item = (ViewGroup) gridThree.findViewById(R.id.item_two);
            itemTile = (TextView) item.findViewById(R.id.item_two_title);
            itemDesc = (TextView) item.findViewById(R.id.item_two_desc);
            itemTag = (TextView) item.findViewById(R.id.item_two_tag);
            itemImg = (GifImageView) item.findViewById(R.id.item_two_img);
            itemGifImg = (GifImageView) item.findViewById(R.id.item_two_gif_img);
            initGridThreeItem(context, entranceItemDtoListEntity, itemTile, itemDesc, itemTag, itemImg, itemGifImg);
        }

        //第三项
        if (size >= 3) {
            entranceItemDtoListEntity = entranceItemDtoListEntities.get(2);
            item = (ViewGroup) gridThree.findViewById(R.id.item_three);
            itemTile = (TextView) item.findViewById(R.id.item_three_title);
            itemDesc = (TextView) item.findViewById(R.id.item_three_desc);
            itemTag = (TextView) item.findViewById(R.id.item_three_tag);
            itemImg = (GifImageView) item.findViewById(R.id.item_three_img);
            itemGifImg = (GifImageView) item.findViewById(R.id.item_three_gif_img);
            initGridThreeItem(context, entranceItemDtoListEntity, itemTile, itemDesc, itemTag, itemImg, itemGifImg);
        }
    }

    /**
     * 初始化GridThree模块的项的方法
     *
     * @param context
     * @param entranceItemDtoListEntity
     * @param itemTile
     * @param itemDesc
     * @param itemTag
     * @param itemImg
     * @param itemGifImg
     */
    private static void initGridThreeItem(Context context, ClassifySubHomeDataBean.ModuleDtoListEntity.EntranceItemDtoListEntity entranceItemDtoListEntity
            , TextView itemTile, TextView itemDesc, TextView itemTag, GifImageView itemImg, GifImageView itemGifImg) {
        itemTile.setText(entranceItemDtoListEntity.getTitle());
        itemTile.setTextColor(Color.parseColor(entranceItemDtoListEntity.getTitleColor()));
        itemDesc.setText(entranceItemDtoListEntity.getDescription());
        List<ClassifySubHomeDataBean.ModuleDtoListEntity.EntranceItemDtoListEntity.NewTagListEntity> tagListEntities = entranceItemDtoListEntity.getNewTagList();
        itemTag.setVisibility(View.INVISIBLE);
        if (tagListEntities != null && !tagListEntities.isEmpty()) {
            itemTag.setVisibility(View.VISIBLE);
            for (ClassifySubHomeDataBean.ModuleDtoListEntity.EntranceItemDtoListEntity.NewTagListEntity tagListEntity : tagListEntities) {
                if (tagListEntity == null || TextUtils.isEmpty(tagListEntity.getName())) {
                    continue;
                }
                itemTag.setText(tagListEntity.getName());
                break;
            }
        }
        Glide.with(context).load(entranceItemDtoListEntity.getImg()).apply(requestOptions).into(itemImg);
        String movieImgUrl = entranceItemDtoListEntity.getMovieImg();
        itemGifImg.setVisibility(View.INVISIBLE);
        if (!TextUtils.isEmpty(movieImgUrl)) {
            itemGifImg.setVisibility(View.VISIBLE);
            Glide.with(context).asGif().load(movieImgUrl).apply(requestOptions).into(itemGifImg);
        }
    }

    /**
     * 根据数据创建Banner模块
     *
     * @param context
     * @param parent
     * @param entity
     * @return
     */
    public static void createBannerLayge750_270(Context context, LinearLayout parent, ClassifySubHomeDataBean.ModuleDtoListEntity entity) {
        if (context == null || parent == null || entity == null) {
            return;
        }

        List<ClassifySubHomeDataBean.ModuleDtoListEntity.EntranceItemDtoListEntity> entranceItemDtoListEntities = entity.getEntranceItemDtoList();

        if (entranceItemDtoListEntities == null || entranceItemDtoListEntities.isEmpty()) {
            return;
        }

        ArrayList<String> images = new ArrayList<String>();
        for (ClassifySubHomeDataBean.ModuleDtoListEntity.EntranceItemDtoListEntity entranceItemDtoListEntity : entranceItemDtoListEntities) {
            images.add(entranceItemDtoListEntity.getImg());
        }

        Banner banner = (Banner) LayoutInflater.from(context).inflate(R.layout.main_classify_module_banner, parent, false);
        //调整布局
        adjustModuleLayout(context, parent, banner);
        parent.addView(banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new BannerGlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    /**
     * 调整模块布局的方法
     *
     * @param parent
     * @param moduleView
     */
    private static void adjustModuleLayout(Context context, LinearLayout parent, View moduleView) {
        if (context == null || parent == null || moduleView == null) {
            return;
        }

        int childCount = parent.getChildCount();
        if (childCount > 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) moduleView.getLayoutParams();
            layoutParams.topMargin = context.getResources().getDimensionPixelSize(R.dimen.main_classify_module_divider_height);
        }
    }

}
