package com.duoduo.main.main.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.commonbusiness.fragment.BaseFragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * 主界面FragmentPagerAdapter
 */
public class MainFragmentPagerAdapter extends BaseFragmentStatePagerAdapter {

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
}
