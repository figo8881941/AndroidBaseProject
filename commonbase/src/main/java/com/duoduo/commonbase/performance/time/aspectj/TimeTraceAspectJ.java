package com.duoduo.commonbase.performance.time.aspectj;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 耗时跟踪注解的AOP处理
 */
@Aspect
public class TimeTraceAspectJ {

    private final String TAG = "TimeTrace";

    @Pointcut("execution(@com.duoduo.commonbase.performance.time.annotation.TimeTrace * *(..))")
    public void timeTrace() {
    }

    @Around("timeTrace()")
    public void timeTraceLog(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object targetObject = joinPoint.getTarget();
        Class targetObjectClass = targetObject.getClass();
        String className = targetObjectClass.getSimpleName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long wastTime = System.currentTimeMillis() - startTime;
        Log.i(TAG, "className : " + className + ", methodName :" + methodName + ", wastTime :" + wastTime + ", this :" + this);
    }

}
