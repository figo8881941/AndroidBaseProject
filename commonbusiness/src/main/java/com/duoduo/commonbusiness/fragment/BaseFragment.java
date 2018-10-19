package com.duoduo.commonbusiness.fragment;

import android.support.v4.app.Fragment;

/**
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    //是否选中
    protected boolean isSelected;

    /**
     * 选中的回调
     */
    public void onSelected() {}

    /**
     * 失去选中的回掉
     */
    public void onUnSelected() {}

    /**
     * 页面滚动
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){}

    /**
     * 滚动状态改变
     * @param state
     */
    public void onPageScrollStateChanged(int state){}

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
