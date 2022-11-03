package com.zzzj.util;

import java.util.Iterator;
import java.util.function.Function;

/**
 * @author zzzj
 * @create 2022-11-02 11:34
 */
public class CopyableIterator<T> implements Iterator<T> {

    private final T origin;
    private final Function<T, T> copyFunction;

    public CopyableIterator(T origin, Function<T, T> copyFunction) {
        this.origin = origin;
        this.copyFunction = copyFunction;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        return copyFunction.apply(origin);
    }

}
