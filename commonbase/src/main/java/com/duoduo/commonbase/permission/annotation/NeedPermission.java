package com.duoduo.commonbase.permission.annotation;

import android.content.Context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
/**
 * 权限检查注解
 */
public @interface NeedPermission {
    /**
     * 需要检查的权限
     */
    String[] permissions();

    /**
     * 是否忽略向用户展示权限说明对话框
     * @return
     */
    boolean ignoreShowRationale() default false;

}
