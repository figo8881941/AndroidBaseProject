package com.duoduo.main.classify.home.view;

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

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.duoduo.main.R;
import com.duoduo.main.base.data.ProductInfoEntity;
import com.duoduo.main.classify.consts.IClassifyConsts;
import com.duoduo.main.classify.home.data.ClassifySubHomeEntity;
import com.duoduo.main.classify.view.ClassifyHotSellAdapter;
import com.duoduo.main.common.image.BannerGlideImageLoader;
import com.duoduo.main.glide.GlideApp;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * 分类首页ViewHelper
 */
public class ClassifySubHomeViewHelper {

    /**
     * 下一个module是否需要增加margintop
     */
    private static boolean shouldAjustNextModuleLayout = true;

    /**
     * 数据初始化headerView的方法
     *
     * @param context
     * @param homeEntity
     */
    public static ClassifySubHomeHeaderView initHeaderViewByData(Context context, ClassifySubHomeEntity homeEntity, ClassifySubHomeHeaderView oldHeaderView) {
        if (context == null || homeEntity == null) {
            return null;
        }
        //如果已经初始化过一次headerview，就重用
        //并且对比数据，看看是否需要刷新
        ClassifySubHomeHeaderView headerView = null;
        if (oldHeaderView != null) {
            headerView = oldHeaderView;
        } else {
            headerView = new ClassifySubHomeHeaderView(context);
            headerView.setOrientation(LinearLayout.VERTICAL);
        }

        //把之前生成的子View保存好
        int childCount = headerView.getChildCount();
        ArrayList<View> childViews = new ArrayList<View>();
        for (int i = 0; i < childCount; i++) {
            childViews.add(headerView.getChildAt(i));
        }

        //移除所有的子View
        headerView.removeAllViews();

        List<ClassifySubHomeEntity.ModuleDtoListEntity> moduleDtoListEntities = homeEntity.getModuleDtoList();
        if (moduleDtoListEntities != null && !moduleDtoListEntities.isEmpty()) {
            //有module
            int size = moduleDtoListEntities.size();
            for (int i = 0, position = 0; i < size; i++) {
                ClassifySubHomeEntity.ModuleDtoListEntity entity = moduleDtoListEntities.get(i);
                if (i < childCount) {
                    //当前数据与子View的数据进行匹配
                    View child = childViews.get(i);
                    Object tag = child.getTag();
                    if (tag instanceof ClassifySubHomeEntity.ModuleDtoListEntity) {
                        ClassifySubHomeEntity.ModuleDtoListEntity oldEntity =
                                (ClassifySubHomeEntity.ModuleDtoListEntity) child.getTag();
                        if (JSON.toJSONString(oldEntity).equals(JSON.toJSONString(entity))) {
                            //老数据跟新数据一致，直接使用旧的子View
                            headerView.addView(child, position);
                            position++;
                            if (i == 0) {
                                shouldAjustNextModuleLayout = false;
                            }
                            continue;
                        }
                    }
                }
                //新老数据不一致，生成新的子View
                boolean add = createModuleView(context, headerView, entity, position);
                if (add) {
                    position++;
                }
            }
        }

        ClassifySubHomeEntity.TopicModuleDtoEntity topicModuleDtoEntity = homeEntity.getTopicModuleDto();
        if (topicModuleDtoEntity != null) {
            createTopicTilteView(context, headerView, topicModuleDtoEntity);
        }
        if (headerView.getChildCount() <= 0) {
            //如果经过上面处理，都没有任务的子view添加，则返回空
            return null;
        }
        return headerView;
    }

    /**
     * 根据数据创建模块
     *
     * @param context
     * @param parent
     * @param entity
     * @return
     */
    public static boolean createModuleView(Context context, ClassifySubHomeHeaderView parent, ClassifySubHomeEntity.ModuleDtoListEntity entity, int position) {
        if (context == null || parent == null || entity == null) {
            return false;
        }
        boolean result = false;
        int moduleType = entity.getType();
        switch (moduleType) {
            case IClassifyConsts
                    .ModuleType.BANNER_LAYGE_750_270: {
                result = createBannerLayge750_270(context, parent, entity, position);
            }
            break;
            case IClassifyConsts
                    .ModuleType.COMMON_GRID_THREE: {
                result = createGridThree(context, parent, entity, position);
            }
            break;
            case IClassifyConsts
                    .ModuleType.BANNER_SMALL: {
                result = createBannerSmall(context, parent, entity, position);
            }
            break;
            case IClassifyConsts
                    .ModuleType.BANNER_SMALL_TWO: {
                result = createBannerSmall(context, parent, entity, position);
            }
            break;
            case IClassifyConsts
                    .ModuleType.HOT_SELL: {
                result = createHotSell(context, parent, entity, position);
            }
            break;
        }
        return result;
    }

    /**
     * 根据数据创建小Banner模块
     *
     * @param context
     * @param parent
     * @param entity
     */
    private static boolean createBannerSmall(Context context, ClassifySubHomeHeaderView parent, ClassifySubHomeEntity.ModuleDtoListEntity entity, int position) {
        if (context == null || parent == null || entity == null) {
            return false;
        }

        List<ClassifySubHomeEntity.ModuleDtoListEntity.EntranceItemDtoListEntity> entranceItemDtoListEntities = entity.getEntranceItemDtoList();

        if (entranceItemDtoListEntities == null || entranceItemDtoListEntities.isEmpty()) {
            return false;
        }
        ViewGroup bannerSmall = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.main_classify_module_banner_small, parent, false);
        //调整布局
        adjustModuleLayout(context, parent, bannerSmall);
        bannerSmall.setTag(entity);
        parent.addView(bannerSmall, position);

        ClassifySubHomeEntity.ModuleDtoListEntity.EntranceItemDtoListEntity entranceItemDtoListEntity = entranceItemDtoListEntities.get(0);
        GifImageView bannerImg = (GifImageView) bannerSmall.findViewById(R.id.banner_img);
        GlideApp.with(context)
                .load(entranceItemDtoListEntity.getImg())
                .applyGlobalOptions()
                .into(bannerImg);
        return true;
    }

    /**
     * 根据数据创建排行榜模块
     *
     * @param context
     * @param parent
     * @param entity
     */
    private static boolean createHotSell(Context context, ClassifySubHomeHeaderView parent, ClassifySubHomeEntity.ModuleDtoListEntity entity, int position) {
        if (context == null || parent == null || entity == null) {
            return false;
        }

        List<ProductInfoEntity> productInfoListEntities = entity.getProductInfoList();

        if (productInfoListEntities == null || productInfoListEntities.isEmpty()) {
            return false;
        }
        ViewGroup hotSell = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.main_classify_module_hot_sell, parent, false);
        //调整布局
        adjustModuleLayout(context, parent, hotSell);
        hotSell.setTag(entity);
        parent.addView(hotSell, position);

        //标题
        TextView titleText = (TextView) hotSell.findViewById(R.id.title_text);
        GifImageView titleImg = (GifImageView) hotSell.findViewById(R.id.title_img);
        String titleString = entity.getTitle();
        String titleImgUrl = entity.getTitleImg();
        if (!TextUtils.isEmpty(titleImgUrl)) {
            titleText.setVisibility(View.GONE);
            titleImg.setVisibility(View.VISIBLE);
            GlideApp.with(context)
                    .load(titleImgUrl)
                    .applyGlobalOptions()
                    .into(titleImg);
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
        return true;
    }

    /**
     * 根据数据创建网格模块
     *
     * @param context
     * @param parent
     * @param entity
     */
    private static boolean createGridThree(Context context, ClassifySubHomeHeaderView parent, ClassifySubHomeEntity.ModuleDtoListEntity entity, int position) {
        if (context == null || parent == null || entity == null) {
            return false;
        }

        List<ClassifySubHomeEntity.ModuleDtoListEntity.EntranceItemDtoListEntity> entranceItemDtoListEntities = entity.getEntranceItemDtoList();

        if (entranceItemDtoListEntities == null || entranceItemDtoListEntities.isEmpty()) {
            return false;
        }

        ViewGroup gridThree = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.main_classify_module_grid_three, parent, false);
        //调整布局
        adjustModuleLayout(context, parent, gridThree);
        gridThree.setTag(entity);
        parent.addView(gridThree, position);

        int size = entranceItemDtoListEntities.size();
        ClassifySubHomeEntity.ModuleDtoListEntity.EntranceItemDtoListEntity entranceItemDtoListEntity = entranceItemDtoListEntities.get(0);

        //第一个项
        ViewGroup item = (ViewGroup) gridThree.findViewById(R.id.item_one);
        GifImageView itemTilteImg = (GifImageView) item.findViewById(R.id.item_one_tilte_img);
        TextView itemTile = (TextView) item.findViewById(R.id.item_one_title);
        TextView itemDesc = (TextView) item.findViewById(R.id.item_one_desc);
        TextView itemTag = (TextView) item.findViewById(R.id.item_one_tag);
        GifImageView itemImg = (GifImageView) item.findViewById(R.id.item_one_img);
        GifImageView itemGifImg = (GifImageView) item.findViewById(R.id.item_one_gif_img);
        initGridThreeItem(context, entranceItemDtoListEntity, itemTilteImg,
                itemTile, itemDesc, itemTag, itemImg, itemGifImg);

        //第二项
        if (size >= 2) {
            entranceItemDtoListEntity = entranceItemDtoListEntities.get(1);
            item = (ViewGroup) gridThree.findViewById(R.id.item_two);
            itemTilteImg = (GifImageView) item.findViewById(R.id.item_two_tilte_img);
            itemTile = (TextView) item.findViewById(R.id.item_two_title);
            itemDesc = (TextView) item.findViewById(R.id.item_two_desc);
            itemTag = (TextView) item.findViewById(R.id.item_two_tag);
            itemImg = (GifImageView) item.findViewById(R.id.item_two_img);
            itemGifImg = (GifImageView) item.findViewById(R.id.item_two_gif_img);
            initGridThreeItem(context, entranceItemDtoListEntity, itemTilteImg,
                    itemTile, itemDesc, itemTag, itemImg, itemGifImg);
        }

        //第三项
        if (size >= 3) {
            entranceItemDtoListEntity = entranceItemDtoListEntities.get(2);
            item = (ViewGroup) gridThree.findViewById(R.id.item_three);
            itemTilteImg = (GifImageView) item.findViewById(R.id.item_three_tilte_img);
            itemTile = (TextView) item.findViewById(R.id.item_three_title);
            itemDesc = (TextView) item.findViewById(R.id.item_three_desc);
            itemTag = (TextView) item.findViewById(R.id.item_three_tag);
            itemImg = (GifImageView) item.findViewById(R.id.item_three_img);
            itemGifImg = (GifImageView) item.findViewById(R.id.item_three_gif_img);
            initGridThreeItem(context, entranceItemDtoListEntity, itemTilteImg,
                    itemTile, itemDesc, itemTag, itemImg, itemGifImg);
        }
        return true;
    }

    /**
     * 初始化GridThree模块的项的方法
     *
     * @param context
     * @param entranceItemDtoListEntity
     * @param itemTitle
     * @param itemDesc
     * @param itemTag
     * @param itemImg
     * @param itemGifImg
     */
    private static void initGridThreeItem(Context context, ClassifySubHomeEntity.ModuleDtoListEntity.EntranceItemDtoListEntity entranceItemDtoListEntity
            , GifImageView itemTitleImg, TextView itemTitle, TextView itemDesc
            , TextView itemTag, GifImageView itemImg, GifImageView itemGifImg) {
        String titleImg = entranceItemDtoListEntity.getTitleImg();
        if (!TextUtils.isEmpty(titleImg)) {
            itemTitleImg.setVisibility(View.VISIBLE);
            itemTitle.setVisibility(View.INVISIBLE);
            Glide.with(context).load(titleImg).into(itemTitleImg);
        } else {
            itemTitleImg.setVisibility(View.INVISIBLE);
            itemTitle.setVisibility(View.VISIBLE);
            itemTitle.setText(entranceItemDtoListEntity.getTitle());
            int itemTitleTextColor = TextUtils.isEmpty(entranceItemDtoListEntity.getTitleColor()) ?
                    Color.BLACK : Color.parseColor(entranceItemDtoListEntity.getTitleColor());
            itemTitle.setTextColor(itemTitleTextColor);
        }
        itemDesc.setText(entranceItemDtoListEntity.getDescription());
        List<ClassifySubHomeEntity.ModuleDtoListEntity.EntranceItemDtoListEntity.NewTagListEntity> tagListEntities = entranceItemDtoListEntity.getNewTagList();
        itemTag.setVisibility(View.INVISIBLE);
        if (tagListEntities != null && !tagListEntities.isEmpty()) {
            itemTag.setVisibility(View.VISIBLE);
            for (ClassifySubHomeEntity.ModuleDtoListEntity.EntranceItemDtoListEntity.NewTagListEntity tagListEntity : tagListEntities) {
                if (tagListEntity == null || TextUtils.isEmpty(tagListEntity.getName())) {
                    continue;
                }
                itemTag.setText(tagListEntity.getName());
                break;
            }
        }
        GlideApp.with(context)
                .load(entranceItemDtoListEntity.getImg())
                .applyGlobalOptions()
                .into(itemImg);
        String movieImgUrl = entranceItemDtoListEntity.getMovieImg();
        itemGifImg.setVisibility(View.INVISIBLE);
        if (!TextUtils.isEmpty(movieImgUrl)) {
            itemGifImg.setVisibility(View.VISIBLE);
            GlideApp.with(context)
                    .asGif()
                    .load(movieImgUrl)
                    .applyGlobalOptions()
                    .into(itemGifImg);
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
    public static boolean createBannerLayge750_270(Context context, ClassifySubHomeHeaderView parent, ClassifySubHomeEntity.ModuleDtoListEntity entity, int position) {
        if (context == null || parent == null || entity == null) {
            return false;
        }

        List<ClassifySubHomeEntity.ModuleDtoListEntity.EntranceItemDtoListEntity> entranceItemDtoListEntities = entity.getEntranceItemDtoList();

        if (entranceItemDtoListEntities == null || entranceItemDtoListEntities.isEmpty()) {
            return false;
        }

        ArrayList<String> images = new ArrayList<String>();
        for (ClassifySubHomeEntity.ModuleDtoListEntity.EntranceItemDtoListEntity entranceItemDtoListEntity : entranceItemDtoListEntities) {
            images.add(entranceItemDtoListEntity.getImg());
        }

        Banner banner = (Banner) LayoutInflater.from(context).inflate(R.layout.main_classify_module_banner, parent, false);
        //调整布局
        adjustModuleLayout(context, parent, banner);
        banner.setTag(entity);
        parent.addView(banner, position);
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
        //接下来的module不需要增加margintop,紧贴banner
        shouldAjustNextModuleLayout = false;
        return true;
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

        if (!shouldAjustNextModuleLayout) {
            shouldAjustNextModuleLayout = true;
            return;
        }

        int childCount = parent.getChildCount();
        if (childCount > 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) moduleView.getLayoutParams();
            layoutParams.topMargin = context.getResources().getDimensionPixelSize(R.dimen.main_classify_module_divider_height);
        }
    }

    /**
     * 创建主题标题
     *
     * @param context
     * @param parent
     * @param topicModuleDtoEntity
     */
    private static void createTopicTilteView(Context context, LinearLayout parent, ClassifySubHomeEntity.TopicModuleDtoEntity topicModuleDtoEntity) {
        if (context == null || parent == null || topicModuleDtoEntity == null) {
            return;
        }
        ViewGroup titleView = (ViewGroup) LayoutInflater.from(context)
                .inflate(R.layout.main_classify_topic_title, parent, false);
        String titleImgUrl = topicModuleDtoEntity.getTitleImg();
        if (!TextUtils.isEmpty(titleImgUrl)) {
            GifImageView titleImgView = titleView.findViewById(R.id.title_img);
            GlideApp.with(context)
                    .load(titleImgUrl)
                    .applyGlobalOptions()
                    .into(titleImgView);
        }
        adjustModuleLayout(context, parent, titleView);
        titleView.setTag(topicModuleDtoEntity);
        parent.addView(titleView);
    }

}
