package com.duoduo.commonbusiness.permission;

import android.content.Context;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.Setting;

import java.util.List;

/**
 * 权限工具类
 */
public class PermissionUtils {

    /**
     * 检查和申请权限的方法
     * @param context
     * @param ignoreShowRationale 是否忽略showRationale
     * @param listener 监听器
     * @param permissions 请求权限
     */
    public static void checkAndRequestPermission(final Context context, final boolean ignoreShowRationale, final ICheckAndRequestPermissionListener listener, final String... permissions) {
        boolean hasPermissions = AndPermission.hasPermissions(context, permissions);
        if (hasPermissions) {
            //已经有权限
            if (listener != null) {
                listener.onGrantedPermission(permissions);
            }
        } else {
            //没有权限
            AndPermission.with(context).runtime().permission(permissions).onGranted(new Action<List<String>>() {
                @Override
                public void onAction(List<String> data) {
                    //授权成功
                    if (listener != null) {
                        listener.onGrantedPermission(permissions);
                    }
                }
            }).onDenied(new Action<List<String>>() {
                @Override
                public void onAction(List<String> data) {
                    //授权拒绝
                    //查看是否勾选了"不再提示"
                    boolean hasAlwaysDeniedPermission = AndPermission.hasAlwaysDeniedPermission(context, permissions);
                    if (hasAlwaysDeniedPermission) {
                        //如果是勾选了"不再提示"，就跳设置界面
                        AndPermission.with(context)
                                .runtime()
                                .setting()
                                .onComeback(new Setting.Action() {
                                    @Override
                                    public void onAction() {
                                        //设置界面返回
                                        //再检查一次是否有权限
                                        boolean hasPermissions = AndPermission.hasPermissions(context, permissions);
                                        if (hasPermissions) {
                                            //如果有权限
                                            if (listener != null) {
                                                listener.onGrantedPermission(permissions);
                                            }
                                        } else {
                                            //如果没有权限，进行拒绝处理
                                            if (listener != null) {
                                                listener.onDeniedPermission(permissions);
                                            }
                                        }
                                    }
                                })
                                .start();
                    } else {
                        //如果没有勾选"不再提示"，进行拒绝处理
                        if (listener != null) {
                            listener.onDeniedPermission(permissions);
                        }
                    }
                }
            }).rationale(new Rationale<List<String>>() {
                @Override
                public void showRationale(Context context, List<String> data, RequestExecutor executor) {
                    //向用户说明
                    if (ignoreShowRationale) {
                        //如果忽略，直接再次申请权限
                        executor.execute();
                    } else {
                        if (listener != null) {
                            listener.onShowRationale(executor, permissions);
                        }
                    }
                }
            }).start();
        }
    }
}
