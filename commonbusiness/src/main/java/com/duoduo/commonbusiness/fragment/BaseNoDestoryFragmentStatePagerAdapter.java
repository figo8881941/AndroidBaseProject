package com.duoduo.commonbusiness.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 基类FragmentStatePagerAdapter
 * 不销毁Fragment
 */
public class BaseNoDestoryFragmentStatePagerAdapter extends BaseFragmentStatePagerAdapter {


    public BaseNoDestoryFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //为了控制资源开销
    //在默认情况下，FragmentStatePagerAdapter会销毁除了当前Fragment及其左右各一个Fragment外的其它Fragment
    //如果想要保持Fragment的状态，这里就注释掉Super的方法，不让其销毁。当然，资源开销会变大。
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }
}
