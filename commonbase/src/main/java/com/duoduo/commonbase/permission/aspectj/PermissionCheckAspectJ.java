package com.duoduo.commonbase.permission.aspectj;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.duoduo.commonbase.permission.ICheckAndRequestPermissionListener;
import com.duoduo.commonbase.permission.PermissionUtils;
import com.duoduo.commonbase.permission.annotation.NeedPermission;
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
                Toast.makeText(context, "onDeniedPermission", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShowRationale(RequestExecutor executor, String... permissions) {
                Toast.makeText(context, "onShowRationale", Toast.LENGTH_SHORT).show();
            }
        }, permissons);
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
                context = ((Context) targetObject).getApplicationContext();
            } else if (targetObject instanceof Fragment) {
                context = ((Fragment) targetObject).getActivity().getApplicationContext();
            } else if (targetObject instanceof android.support.v4.app.Fragment) {
                context = ((android.support.v4.app.Fragment) targetObject).getActivity().getApplicationContext();
            } else if (targetObject instanceof View) {
                context = ((View)targetObject).getContext().getApplicationContext();
            }else {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }
}
