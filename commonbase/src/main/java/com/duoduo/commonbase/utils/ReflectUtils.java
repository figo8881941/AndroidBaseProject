package com.duoduo.commonbase.utils;

import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public class ReflectUtils {

    /**
     * 获取某个类里面有某个注解的方法
     * 如果有多个方法使用了同一个注解，返回第一个找到的
     *
     * @param classObject
     * @param annotationClass
     * @return
     */
    public static Method getMethodByAnnotation(Class classObject, Class annotationClass) {
        Method method = null;
        try {
            Method[] methods = classObject.getDeclaredMethods();
            if (methods != null) {
                int length = methods.length;
                for (int i = 0; i < length; i++) {
                    Method methodItem = methods[i];
                    boolean isAnnotationPresent = methodItem.isAnnotationPresent(annotationClass);
                    if (isAnnotationPresent) {
                        method = methodItem;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }
}

