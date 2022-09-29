package com.zzzj.util;

import java.util.Iterator;

/**
 * @author zzzj
 * @create 2022-09-29 17:12
 */
public class CopyIterator implements Iterator<int[]> {

    private final int[] origin;

    public CopyIterator(int[] origin) {
        this.origin = origin;
    }

    public static CopyIterator fromArray(int[] origin) {
        return new CopyIterator(origin);
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
