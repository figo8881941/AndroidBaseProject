package com.duoduo.main.classify.view;

import android.content.Context;
import android.content.res.Resources;

import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.main.classify.data.ClassifySubTabEntity;
import com.duoduo.main.classify.home.ClassifySubHomeFragment;
import com.duoduo.main.classify.subclassify.ClassifySubTabFragment;
import com.duoduo.main.main.consts.IMainConsts;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类Fragment辅助类
 */
public class ClassifySubFragmentHelper {

    /**
     * 创建初始的分类Fragment集合的方法
     *
     * @return
     */
    public static ArrayList<BaseFragment> createInitClassifySubFragmentList(Context context) {
        Resources resources = context.getResources();
        ArrayList<BaseFragment> fragments = new ArrayList<BaseFragment>();
        BaseFragment homeFragment = new ClassifySubHomeFragment();
        ClassifySubTabEntity.CategoryNewListEntity entity = new ClassifySubTabEntity.CategoryNewListEntity();
        entity.setCategoryName("");
        entity.setId(IMainConsts.MainTabIdValue.HOME_TAB);
        homeFragment.setData(entity);
        fragments.add(homeFragment);
        return fragments;
    }

    /**
     * 根据数据，创建分类Fragment集合的方法
     *
     * @param classifySubTabEntity
     * @return
     */
    public static ArrayList<BaseFragment> createClassifySubFragmentList(ClassifySubTabEntity classifySubTabEntity) {
        if (classifySubTabEntity == null) {
            return null;
        }
        List<ClassifySubTabEntity.CategoryNewListEntity> tabListEntityList = classifySubTabEntity.getCategoryNewList();
        if (tabListEntityList == null || tabListEntityList.isEmpty()) {
            return null;
        }
        ArrayList<BaseFragment> fragments = new ArrayList<BaseFragment>();
        for (ClassifySubTabEntity.CategoryNewListEntity entity : tabListEntityList) {
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
    private static BaseFragment createFragment(ClassifySubTabEntity.CategoryNewListEntity entity) {
        if (entity == null) {
            return null;
        }
        BaseFragment fragment = new ClassifySubTabFragment();

        if (fragment != null) {
            fragment.setData(entity);
        }

        return fragment;
    }
}
