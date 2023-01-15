package com.zzzj.util;

public interface ExecutionCallback {

    void beforeExecute(int loopCount, InvokableExp exp, InvokeMethodSource source, Object[] args);

    void afterExecute(int loopCount, InvokableExp exp, InvokeMethodSource source, Object result);

}
