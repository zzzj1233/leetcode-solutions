package com.zzzj.util;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LeetCodeExp {

    protected final List<String> args;

    protected final List<String> methods;

    public LeetCodeExp() {
        this.methods = new ArrayList<>();

        this.args = new ArrayList<>();
    }

    public LeetCodeExp(String instanceName, String... args) {
        this();
        this.invokeMethod(instanceName, args);
    }

    public Object invokeMethod(String instanceName, Object... args) {
        this.methods.add(instanceName);
        this.args.add(args == null ? StrUtil.EMPTY : Arrays.stream(args).map(Object::toString).collect(Collectors.joining(",")));
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (String method : methods) {
            builder.append(StrUtil.wrap(method, "\""));
            builder.append(",");
        }
        builder.setLength(builder.length() - 1);
        builder.append("]");
        builder.append("\n");
        builder.append("[");
        for (String arg : args) {
            builder.append(StrUtil.wrap(arg, "[", "]"));
            builder.append(",");
        }
        builder.setLength(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }
}
