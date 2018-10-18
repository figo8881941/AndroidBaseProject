package com.duoduo.main.main.view;

import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duoduo.main.R;
import com.duoduo.main.main.data.MainTabDataBean;

import java.util.List;

/**
 * Tab工厂类
 */
public class MainTabFactory {

    /**
     * 创建Tab的方法
     *
     * @param tabLayout
     * @param mainTabDataBean
     */
    public static void createTabByData(TabLayout tabLayout, MainTabDataBean mainTabDataBean) {
        if (tabLayout == null) {
            return;
        }
        tabLayout.removeAllTabs();
        if (mainTabDataBean == null) {
            return;
        }
        List<MainTabDataBean.TabListEntity> entityList = mainTabDataBean.getTabList();
        if (entityList != null && !entityList.isEmpty()) {
            LayoutInflater inflater = LayoutInflater.from(tabLayout.getContext().getApplicationContext());
            for (MainTabDataBean.TabListEntity entity : entityList) {
                TabLayout.Tab tab = tabLayout.newTab();
                ViewGroup customView = (ViewGroup) inflater.inflate(R.layout.main_main_tabitem_layout, null);
                ImageView itemIcon = (ImageView) customView.findViewById(R.id.item_icon);
                Glide.with(itemIcon).load(entity.getTabImg()).into(itemIcon);
                TextView itemName = (TextView) customView.findViewById(R.id.item_name);
                itemName.setText(entity.getTabName());
                tab.setCustomView(customView);
                tabLayout.addTab(tab);
            }
        }
    }
}
