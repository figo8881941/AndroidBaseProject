package com.duoduo.main.main.view;

import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.duoduo.main.R;
import com.duoduo.main.glide.GlideApp;
import com.duoduo.main.main.data.MainTabEntity;

import java.util.List;

/**
 * Tab辅助类
 */
public class MainTabHelper {

    /**
     * 创建Tab的方法
     *
     * @param tabLayout
     * @param mainTabEntity
     */
    public static void createTabByData(TabLayout tabLayout, MainTabEntity mainTabEntity) {
        if (tabLayout == null) {
            return;
        }
        tabLayout.removeAllTabs();
        if (mainTabEntity == null) {
            return;
        }
        List<MainTabEntity.TabListEntity> entityList = mainTabEntity.getTabList();
        if (entityList != null && !entityList.isEmpty()) {
            LayoutInflater inflater = LayoutInflater.from(tabLayout.getContext().getApplicationContext());
            for (MainTabEntity.TabListEntity entity : entityList) {
                TabLayout.Tab tab = tabLayout.newTab();
                ViewGroup customView = (ViewGroup) inflater.inflate(R.layout.main_main_tabitem_layout, null);
                customView.setTag(entity);
                ImageView itemIcon = (ImageView) customView.findViewById(R.id.item_icon);
                GlideApp.with(itemIcon)
                        .load(entity.getTabImgV2())
                        .applyGlobalOptions()
                        .into(itemIcon);
                TextView itemName = (TextView) customView.findViewById(R.id.item_name);
                itemName.setText(entity.getTabName());
                tab.setCustomView(customView);
                tabLayout.addTab(tab);
            }
        }
    }

    /**
     * 切换tab为已选择样式
     *
     * @param tab
     */
    public static void changeTabToSelectedStyle(TabLayout.Tab tab) {
        if (tab == null || tab.getCustomView() == null) {
            return;
        }
        ViewGroup customView = (ViewGroup) tab.getCustomView();
        MainTabEntity.TabListEntity entity = (MainTabEntity.TabListEntity) customView.getTag();
        ImageView itemIcon = (ImageView) customView.findViewById(R.id.item_icon);
        GlideApp.with(itemIcon)
                .load(entity.getTabSelectedImgV2())
                .applyGlobalOptions()
                .into(itemIcon);
        TextView itemName = (TextView) customView.findViewById(R.id.item_name);
        int textColor = customView.getContext().getResources().getColor(R.color.main_main_main_tab_item_text_selected_color);
        itemName.setTextColor(textColor);
    }

    /**
     * 切换tab为未选择样式
     *
     * @param tab
     */
    public static void changeTabToUnSelectedStyle(TabLayout.Tab tab) {
        if (tab == null || tab.getCustomView() == null) {
            return;
        }
        ViewGroup customView = (ViewGroup) tab.getCustomView();
        MainTabEntity.TabListEntity entity = (MainTabEntity.TabListEntity) customView.getTag();
        ImageView itemIcon = (ImageView) customView.findViewById(R.id.item_icon);
        GlideApp.with(itemIcon)
                .load(entity.getTabImgV2())
                .applyGlobalOptions()
                .into(itemIcon);
        TextView itemName = (TextView) customView.findViewById(R.id.item_name);
        int textColor = customView.getContext().getResources().getColor(R.color.main_main_main_tab_item_text_unselected_color);
        itemName.setTextColor(textColor);
    }
}
