package com.zzzj.util;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LeetCodeExp {

    protected final List<Object[]> args;

    protected final List<String> methods;
    private String instanceName;

    private String instanceArgs;

    public LeetCodeExp() {
        this.methods = new ArrayList<>();

        this.args = new ArrayList<>();
    }

    public LeetCodeExp(String instanceName, Object... args) {
        this();
        this.instanceName = instanceName;
        this.instanceArgs = args == null ? StrUtil.EMPTY : Arrays.stream(args).map(Object::toString).collect(Collectors.joining(","));
    }

    public Object invokeMethod(String instanceName, Object... args) {
        this.methods.add(instanceName);
        this.args.add(args == null ? new Object[0] : args);
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(StrUtil.wrap(instanceName, "\""));
        builder.append(",");
        for (String method : methods) {
            builder.append(StrUtil.wrap(method, "\""));
            builder.append(",");
        }
        builder.setLength(builder.length() - 1);
        builder.append("]");
        builder.append("\n");
        builder.append("[");
        builder.append(StrUtil.wrap(instanceArgs, "[", "]"));
        builder.append(",");

        List<String> args = this.args.stream()
                .map(objects -> Arrays.stream(objects).map(Object::toString).collect(Collectors.joining(",")))
                .collect(Collectors.toList());

        for (String arg : args) {
            builder.append(StrUtil.wrap(arg, "[", "]"));
            builder.append(",");
        }

        builder.setLength(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

    public void clear() {
        this.methods.clear();
        this.args.clear();
    }
}
