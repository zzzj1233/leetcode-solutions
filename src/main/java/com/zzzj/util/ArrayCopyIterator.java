package com.zzzj.util;

import java.util.Iterator;

/**
 * @author zzzj
 * @create 2022-09-29 17:12
 */
public class ArrayCopyIterator implements Iterator<int[]> {

    private final int[] origin;

    public ArrayCopyIterator(int[] origin) {
        this.origin = origin;
    }

    public static ArrayCopyIterator fromArray(int[] origin) {
        return new ArrayCopyIterator(origin);
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public int[] next() {
        return ArrayUtil.copy(origin);
    }

}
