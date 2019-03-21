package com.duoduo.commonbusiness.net.common;

/**
 * 网络请求回调
 */
public interface IRequestCallback<T extends Object> {

    /**
     * 网络请求失败回调
     * @param e
     */
    public void onFailure(Exception e);

    /**
     * 网络请求成功回调
     * @param responseData
     */
    public void onSuccess(T responseData);
}
