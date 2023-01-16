package com.zzzj.util;

import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Method;

public class InvokableExp extends LeetCodeExp {

    private final Object instance;

    public InvokableExp(Class<?> instanceClass, Object... args) {
        Object newInstance = ReflectUtil.newInstance(instanceClass, args);

        if (newInstance == null) {
            throw new RuntimeException("Can't create the instance of " + instanceClass, null);
        }

        this.instance = newInstance;

        super.invokeMethod(instanceClass.getSimpleName(), args);
    }

    public Object invokeMethod(String methodName, Object... args) {
        Method method = ReflectUtil.getMethodByName(instance.getClass(), methodName);
        Object result = ReflectUtil.invoke(instance, method, args);
        super.invokeMethod(methodName, args);
        return result;
    }

}
