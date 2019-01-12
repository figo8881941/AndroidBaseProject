package com.duoduo.commonbase.permission.entity;

/**
 * 权限拒绝Entity
 */
public class DeniedPermissionEntity {

    private String[] permissions;
    private int requestCode;

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
}
