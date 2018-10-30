package com.duoduo.main.classify.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.duoduo.commonbusiness.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * 主界面FragmentPagerAdapter
 */
public class ClassifySubFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<BaseFragment> fragments;

    public ClassifySubFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment item = fragments == null ? null : fragments.get(position);
        return item;
    }

    @Override
    public int getCount() {
        int size = fragments == null ? 0 : fragments.size();
        return size;
    }

    public ArrayList<BaseFragment> getFragments() {
        return fragments;
    }

    public void setFragments(ArrayList<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    public void destory() {
        if (fragments != null) {
            fragments.clear();
            fragments = null;
        }
    }
}
