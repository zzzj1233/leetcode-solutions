package com.zzzj.util;

import java.util.function.Supplier;

public class InvokeMethodSource {
    private final String methodName;

    private final Supplier<Object[]> paramsSupplier;

    private static final Supplier<Object[]> EMPTY_SUPPLIER = () -> new Object[0];

    public InvokeMethodSource(String methodName, Supplier<Object[]> paramsSupplier) {
        this.methodName = methodName;
        this.paramsSupplier = paramsSupplier;
    }

    public InvokeMethodSource(String methodName) {
        this(methodName, EMPTY_SUPPLIER);
    }

    public String getMethodName() {
        return methodName;
    }

    public Supplier<Object[]> getParamsSupplier() {
        return paramsSupplier;
    }
}
