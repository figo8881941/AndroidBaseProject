package com.duoduo.main.classify.view;

import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.main.classify.data.ClassifyTabDataBean;
import com.duoduo.main.classify.subclassify.ClassifySubFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类Fragment工厂类
 */
public class ClassifySubFragmentFactory {

    /**
     * 根据数据，创建分类Fragment集合的方法
     *
     * @param classifyTabDataBean
     * @return
     */
    public static ArrayList<BaseFragment> createClassifySubFragmentList(ClassifyTabDataBean classifyTabDataBean) {
        if (classifyTabDataBean == null) {
            return null;
        }
        List<ClassifyTabDataBean.CategoryListEntity> tabListEntityList = classifyTabDataBean.getCategoryList();
        if (tabListEntityList == null || tabListEntityList.isEmpty()) {
            return null;
        }
        ArrayList<BaseFragment> fragments = new ArrayList<BaseFragment>();
        for (ClassifyTabDataBean.CategoryListEntity entity : tabListEntityList) {
            BaseFragment fragment = createFragment(entity);
            if (fragment == null) {
                continue;
            }
            fragments.add(fragment);
        }
        return fragments;
    }

    /**
     * 根据数据，创建Fragment的方法
     *
     * @param entity
     * @return
     */
    private static BaseFragment createFragment(ClassifyTabDataBean.CategoryListEntity entity) {
        if (entity == null) {
            return null;
        }
        BaseFragment fragment = new ClassifySubFragment();

        if (fragment != null) {
            fragment.setData(entity);
        }

        return fragment;
    }
}
