package com.duoduo.commonbusiness.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * 基类FragmentStatePagerAdapter
 */
public class BaseFragmentStatePagerAdapter extends FragmentStatePagerAdapter {


    private ArrayList<BaseFragment> fragments;

    public BaseFragmentStatePagerAdapter(FragmentManager fm) {
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
