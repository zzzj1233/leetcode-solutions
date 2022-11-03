package com.zzzj.util;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-29 17:12
 */
public class ArrayCopyIterator extends CopyableIterator<int[]> {

    public ArrayCopyIterator(int[] origin) {
        this(origin, false);
    }

    public ArrayCopyIterator(int[] origin, boolean sort) {
        super(origin, ArrayUtil::copy);
        if (sort) {
            Arrays.sort(origin);
        }
    }

    public static ArrayCopyIterator fromArray(int[] arr) {
        return new ArrayCopyIterator(arr);
    }

}
