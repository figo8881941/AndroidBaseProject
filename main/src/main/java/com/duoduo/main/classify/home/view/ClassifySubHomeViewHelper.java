package com.duoduo.main.classify.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.duoduo.main.R;
import com.duoduo.main.classify.home.consts.IClassifySubHomeConsts;
import com.duoduo.main.classify.home.data.ClassifySubHomeDataBean;
import com.duoduo.main.common.image.BannerGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类首页ViewHelper
 */
public class ClassifySubHomeViewHelper {

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
            case IClassifySubHomeConsts
                    .ModuleType.BANNER_LAYGE_750_270: {
                createBannerLayge750_270(context, parent, entity);
            }
            break;
            case IClassifySubHomeConsts
                    .ModuleType.COMMON_GRID_THREE: {

            }
            break;
            case IClassifySubHomeConsts
                    .ModuleType.BANNER_SMALL: {

            }
            break;
        }
    }

    /**
     * 根据数据创建模块
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
        ArrayList<String> titles = new ArrayList<String>();
        for (ClassifySubHomeDataBean.ModuleDtoListEntity.EntranceItemDtoListEntity entranceItemDtoListEntity : entranceItemDtoListEntities) {
            images.add(entranceItemDtoListEntity.getImg());
            titles.add(entranceItemDtoListEntity.getTitle());
        }

        Banner banner = (Banner) LayoutInflater.from(context).inflate(R.layout.main_classify_sub_home_fragment_banner, null);
        parent.addView(banner, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new BannerGlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

}