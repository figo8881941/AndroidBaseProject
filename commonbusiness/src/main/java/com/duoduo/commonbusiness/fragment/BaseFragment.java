package com.duoduo.commonbusiness.fragment;

import android.support.v4.app.Fragment;

/**
 * Fragment基类
 */
public abstract class BaseFragment<T> extends Fragment {

    /**
     * 是否选中的标志
     */
    protected boolean isSelected;

    /**
     * 是否被销毁的标志
     */
    protected boolean isDestroy;

    /**
     * 范型数据
     */
    protected T data;

    /**
     * 选中的回调
     */
    public void onSelected() {
        isSelected = true;
    }

    /**
     * 失去选中的回掉
     */
    public void onUnSelected() {
        isSelected = false;
    }

    /**
     * 页面滚动
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /**
     * 滚动状态改变
     *
     * @param state
     */
    public void onPageScrollStateChanged(int state) {
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public void onDestroy() {
        isDestroy = true;
        super.onDestroy();
    }
}
