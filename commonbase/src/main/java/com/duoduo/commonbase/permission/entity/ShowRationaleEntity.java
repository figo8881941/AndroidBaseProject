package com.duoduo.commonbase.permission.entity;

import com.yanzhenjie.permission.RequestExecutor;

/**
 * 显示权限说明对话框Entity
 */
public class ShowRationaleEntity {

    private String[] permissions;
    private int requestCode;
    private RequestExecutor executor;

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public RequestExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(RequestExecutor executor) {
        this.executor = executor;
    }
}
