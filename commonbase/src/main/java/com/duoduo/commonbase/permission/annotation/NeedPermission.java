package com.duoduo.commonbase.permission.annotation;

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

    /**
     * 请求码
     * @return
     */
    int requestCode() default -1;

    /**
     * 当授权拒绝时，是否继续执行原方法
     * @return
     */
    boolean continueWhenDenied() default false;

    /**
     * 一次程序运行，是否只进行一次权限检查
     * @return
     */
    boolean once() default false;
}
