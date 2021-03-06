package com.duoduo.main.classify.view;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.commonbusiness.fragment.BaseFragmentStatePagerAdapter;
import com.duoduo.commonbusiness.fragment.BaseNoDestoryFragmentStatePagerAdapter;
import com.duoduo.main.classify.data.ClassifySubTabEntity;

/**
 * 分类界面FragmentPagerAdapter
 */
public class ClassifySubFragmentPagerAdapter extends BaseNoDestoryFragmentStatePagerAdapter {

    public ClassifySubFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //这里返回title，供PagerSlidingTabStrip使用
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if (fragments != null) {
            BaseFragment fragment = fragments.get(position);
            if (fragment != null && fragment.getData() != null) {
                ClassifySubTabEntity.CategoryNewListEntity entity = (ClassifySubTabEntity.CategoryNewListEntity) fragment.getData();
                title = entity.getCategoryName();
            }
        }
        return title;
    }
}
