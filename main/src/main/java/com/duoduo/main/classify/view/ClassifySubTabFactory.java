package com.duoduo.main.classify.view;

import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duoduo.main.R;
import com.duoduo.main.classify.data.ClassifyTabDataBean;

import java.util.List;

/**
 * 分类Tab工厂类
 */
public class ClassifySubTabFactory {

    /**
     * 创建Tab的方法
     *
     * @param tabLayout
     * @param classifyTabDataBean
     */
    public static void createTabByData(TabLayout tabLayout, ClassifyTabDataBean classifyTabDataBean) {
        if (tabLayout == null) {
            return;
        }
        tabLayout.removeAllTabs();
        if (classifyTabDataBean == null) {
            return;
        }
        List<ClassifyTabDataBean.CategoryListEntity> entityList = classifyTabDataBean.getCategoryList();
        if (entityList != null && !entityList.isEmpty()) {
            LayoutInflater inflater = LayoutInflater.from(tabLayout.getContext().getApplicationContext());
            for (ClassifyTabDataBean.CategoryListEntity entity : entityList) {
                TabLayout.Tab tab = tabLayout.newTab();
                ViewGroup customView = (ViewGroup) inflater.inflate(R.layout.main_classify_fragment_tabitem_layout, null);
                customView.setTag(entity);
                TextView itemName = (TextView) customView.findViewById(R.id.item_name);
                itemName.setText(entity.getCategoryName());
                tab.setCustomView(customView);
                tabLayout.addTab(tab);
            }
        }
    }
}
