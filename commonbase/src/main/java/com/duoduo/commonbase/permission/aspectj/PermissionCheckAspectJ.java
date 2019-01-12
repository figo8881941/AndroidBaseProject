package com.duoduo.commonbase.permission.aspectj;

import android.app.Fragment;
import android.content.Context;
import android.view.View;

import com.duoduo.commonbase.permission.ICheckAndRequestPermissionListener;
import com.duoduo.commonbase.permission.PermissionUtils;
import com.duoduo.commonbase.permission.annotation.DeniedPermission;
import com.duoduo.commonbase.permission.annotation.NeedPermission;
import com.duoduo.commonbase.permission.entity.DeniedPermissionEntity;
import com.duoduo.commonbase.permission.entity.ShowRationaleEntity;
import com.duoduo.commonbase.utils.ReflectUtils;
import com.yanzhenjie.permission.RequestExecutor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 权限检查注解的AOP处理
 */
@Aspect
public class PermissionCheckAspectJ {

    private final String TAG = "PermissionCheckAspectJ";

    @Pointcut("execution(@com.duoduo.commonbase.permission.annotation.NeedPermission * *(..))")
    public void needPermission() {
    }

    @Around("needPermission()")
    public void checkPermission(final ProceedingJoinPoint joinPoint) throws Throwable {
        //尝试获取Context
        final Context context = tryToGetContext(joinPoint);

        //如果context为空，走授权失败
        if (context == null) return;

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        NeedPermission needPermission = method.getAnnotation(NeedPermission.class);
        String[] permissons = needPermission.permissions();
        boolean ignoreShowRationale = needPermission.ignoreShowRationale();
        final int requestCode = needPermission.requestCode();
        //调用工具方法进行权限检查
        PermissionUtils.checkAndRequestPermission(context, ignoreShowRationale, new ICheckAndRequestPermissionListener() {
            @Override
            public void onGrantedPermission(String... permissions) {
                //已授权，继续执行
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void onDeniedPermission(String... permissions) {
                //授权拒绝
                handleDeniedPermission(joinPoint, requestCode, permissions);
            }

            @Override
            public void onShowRationale(RequestExecutor executor, String... permissions) {
                //展示权限说明对话框
                handleShowRationable(joinPoint, requestCode, executor, permissions);
            }
        }, permissons);
    }

    /**
     * 处理授权拒绝
     *
     * @param joinPoint
     * @param permissions
     */
    private void handleDeniedPermission(ProceedingJoinPoint joinPoint, int requestCode
            , String... permissions) {
        try {
            Object targetObject = joinPoint.getTarget();
            Class targetObjectClass = targetObject.getClass();
            Method method = ReflectUtils.getMethodByAnnotation(targetObjectClass, DeniedPermission.class);
            if (method != null) {
                method.setAccessible(true);
                DeniedPermissionEntity entity = new DeniedPermissionEntity();
                entity.setPermissions(permissions);
                entity.setRequestCode(requestCode);
                method.invoke(targetObject, entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理展示权限说明对话框
     *
     * @param joinPoint
     * @param executor
     * @param permissions
     */
    private void handleShowRationable(ProceedingJoinPoint joinPoint, int requestCode, RequestExecutor executor, String... permissions) {
        try {
            Object targetObject = joinPoint.getTarget();
            Class targetObjectClass = targetObject.getClass();
            Method method = ReflectUtils.getMethodByAnnotation(targetObjectClass, DeniedPermission.class);
            if (method != null) {
                method.setAccessible(true);
                ShowRationaleEntity entity = new ShowRationaleEntity();
                entity.setPermissions(permissions);
                entity.setExecutor(executor);
                entity.setRequestCode(requestCode);
                method.invoke(targetObject, entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 尝试获取Context的方法
     *
     * @param joinPoint
     * @return
     */
    private Context tryToGetContext(ProceedingJoinPoint joinPoint) {
        Context context = null;
        try {
            Object targetObject = joinPoint.getTarget();
            if (targetObject instanceof Context) {
                context = (Context) targetObject;
            } else if (targetObject instanceof Fragment) {
                context = ((Fragment) targetObject).getActivity();
            } else if (targetObject instanceof android.support.v4.app.Fragment) {
                context = ((android.support.v4.app.Fragment) targetObject).getActivity();
            } else if (targetObject instanceof View) {
                context = ((View) targetObject).getContext();
            } else if (targetObject instanceof IContextHolder) {
                context = ((IContextHolder) targetObject).getContext();
            } else {
                //如果上面的类型都不是
                //尝试通过反射，获取targetObject的Context字段
                //如果没有Context字段，则获取失败
                Class targetObjectClass = targetObject.getClass();
                for (Class forclass = targetObjectClass; !forclass.equals(Object.class) && context == null; forclass = forclass.getSuperclass()) {
                    Field[] fields = forclass.getDeclaredFields();
                    if (fields != null) {
                        int size = fields.length;
                        for (int i = 0; i < size; i++) {
                            Field field = fields[i];
                            Class fieldType = field.getType();
                            if (fieldType.equals(Context.class)) {
                                field.setAccessible(true);
                                context = (Context) field.get(targetObject);
                                break;
                            }
                        }
                    }
                }
            }
            if (context != null) {
                context = context.getApplicationContext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }
}
