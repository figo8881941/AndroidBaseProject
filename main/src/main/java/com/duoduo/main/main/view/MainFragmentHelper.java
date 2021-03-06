package com.duoduo.main.main.view;

import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.main.category.CategoryFragment;
import com.duoduo.main.classify.ClassifyFragment;
import com.duoduo.main.collect.CollectFragment;
import com.duoduo.main.home.HomeFragment;
import com.duoduo.main.largecoupon.LargeCouponFragment;
import com.duoduo.main.main.consts.IMainConsts;
import com.duoduo.main.main.data.MainTabEntity;
import com.duoduo.main.mine.MineFragment;
import com.duoduo.main.shipping.ShippingFragment;
import com.duoduo.main.web.H5Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面Fragment辅助类
 */
public class MainFragmentHelper {

    /**
     * 根据数据，创建主界面Fragment集合的方法
     *
     * @param mainTabEntity
     * @return
     */
    public static ArrayList<BaseFragment> createMainFragmentList(MainTabEntity mainTabEntity) {
        if (mainTabEntity == null) {
            return null;
        }
        List<MainTabEntity.TabListEntity> tabListEntityList = mainTabEntity.getTabList();
        if (tabListEntityList == null || tabListEntityList.isEmpty()) {
            return null;
        }
        ArrayList<BaseFragment> fragments = new ArrayList<BaseFragment>();
        for (MainTabEntity.TabListEntity entity : tabListEntityList) {
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
    private static BaseFragment createFragment(MainTabEntity.TabListEntity entity) {
        if (entity == null) {
            return null;
        }
        BaseFragment fragment = null;
        //先根据id进行匹配
        int tabid = entity.getId();
        switch (tabid) {
            case IMainConsts
                    .MainTabIdValue.HOME_TAB: {
                fragment = new HomeFragment();
            }
            break;
            case IMainConsts
                    .MainTabIdValue.MINE_TAB: {
                fragment = new MineFragment();
            }
            break;
            case IMainConsts
                    .MainTabIdValue.SHIPPING_TAB: {
                fragment = new ShippingFragment();
            }
            break;
            case IMainConsts
                    .MainTabIdValue.LARGE_AMOUNT_COUPON_TAB: {
                fragment = new LargeCouponFragment();
            }
            break;
            case IMainConsts
                    .MainTabIdValue.CATEGORY_TAB: {
                fragment = new CategoryFragment();
            }
            break;
            case IMainConsts
                    .MainTabIdValue.COLLECTION_TAB: {
                fragment = new CollectFragment();
            }
            break;
            default: {

            }
        }

        //如果id匹配不上，再去匹配tabtype
        if (fragment == null) {
            int tabtype = entity.getTabType();
            switch (tabtype) {
                case IMainConsts.MainTabTypeValue.TYPE_CLASSIFY: {
                    fragment = new ClassifyFragment();
                }
                break;
                case IMainConsts.MainTabTypeValue.TYPE_H5: {
                    fragment = new H5Fragment();
                }
                break;
                default: {

                }
            }
        }

        if (fragment != null) {
            fragment.setData(entity);
        }

        return fragment;
    }
}
