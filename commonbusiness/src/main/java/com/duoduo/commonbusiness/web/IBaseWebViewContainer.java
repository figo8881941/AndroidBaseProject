package com.duoduo.commonbusiness.web;


import android.app.Activity;


/**
 * webview容器接口
 * 
 * @author wangzhuobin
 *
 */
public interface IBaseWebViewContainer {

	/**
	 * 获取activity
	 * @return
	 */
	public Activity getActivity();

	/**
	 * 显示loading对话框
	 */
	public void showLoadingDialog();

	/**
	 * 隐藏loading对话框
	 */
	public void hideLoadingDialog();

	/**
	 * 显示页面loading
	 */
	public void showLoadingPage();

	/**
	 * 隐藏页面loading
	 */
	public void hideLoadingPage();

	/**
	 * 开启／关闭下拉刷新的方法
	 * enable = 1，开启; 否则，关闭
	 * @param enable
	 */
	public void enablePullToRefresh(boolean enable);

	/**
	 * 开启／关闭 启动登录后自动刷新页面的方法
	 * enable = 1，开启; 否则，关闭
	 * @param enable
	 */
	public void enableReloadWhenLogin(boolean enable);

	/**
	 * 开启／关闭回调javascript:onResume()和javascript:onPause()的方法
	 * enable = true，开启; 否则，关闭
	 * @param enable
	 */
	public void enableOnResumeOnPause(boolean enable);

	/**
	 * 开启／关闭回调javascript:onBackPressed()的方法
	 * enable = true，开启; 否则，关闭
	 * @param enable
	 */
	public void enableOnBackPressed(boolean enable);

	/**
	 * 下拉刷新
	 */
	public void pullToRefresh();

	/**
	 * 刷新完成
	 */
	public void onRefreshComplete();

	/**
	 * 关闭页面的方法
	 */
	public void close();

	/**
	 * 设置标题栏左边按钮的方法
	 * @param data
	 */
	public void setActionButtons(String data);

	/**
	 * 重新加载的方法
	 */
	public void reload();
}
