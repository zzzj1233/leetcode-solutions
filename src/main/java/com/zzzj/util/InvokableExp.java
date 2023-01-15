package com.zzzj.util;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InvokableExp extends LeetCodeExp {

    private final Object instance;

    private final List<Object> results;
    private final Object[] instanceArgs;
    private final Class<?> instanceClass;

    private boolean reInvoking;

    public InvokableExp(Class<?> instanceClass, Object... instanceArgs) {
        super(instanceClass.getSimpleName(), instanceArgs);

        this.instanceArgs = instanceArgs;

        this.instanceClass = instanceClass;

        Object newInstance = ReflectUtil.newInstance(instanceClass, instanceArgs);

        if (newInstance == null) {
            throw new RuntimeException("Can't create the instance of " + instanceClass, null);
        }

        results = new ArrayList<>();

        this.instance = newInstance;
    }

    public Object invokeMethod(String methodName, Object... args) {
        Method method = ReflectUtil.getMethodByName(instance.getClass(), methodName);
        Object result = null;
        RuntimeException e = null;
        try {
            result = ReflectUtil.invoke(instance, method, args);
        } catch (UtilException exception) {
            e = exception;
        }
        super.invokeMethod(methodName, args);
        if (e != null) {
            throw e;
        }
        results.add(result);
        return result;
    }

    public Object getInstance() {
        return instance;
    }

    public List<Object> getResults(boolean ignoreNull) {
        if (ignoreNull) {
            return results.stream().filter(Objects::nonNull).collect(Collectors.toList());
        }
        return results;
    }

    public List<Object> getResults() {
        return getResults(false);
    }

    @Override
    public void clear() {
        super.clear();
        this.results.clear();
    }

    public void reInvoke() {
        Object newInstance = ReflectUtil.newInstance(instanceClass, instanceArgs);
        this.reInvoking = true;
        try {
            for (int i = 0; i < methods.size(); i++) {
                ReflectUtil.invoke(newInstance, methods.get(i), args.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.reInvoking = false;
        }
    }

    public boolean isReInvoking() {
        return reInvoking;
    }
}
