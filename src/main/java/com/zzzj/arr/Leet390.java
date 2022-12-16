package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2022-12-05 11:22
 */
public class Leet390 {

    public int process(int L, int R, int i, int remain, boolean dir) {
        if (L == R) {
            return L;
        }
        if ((remain & 1) == 1) {
            return process(L + i, R - i, i << 1, remain >> 1, !dir);
        } else {
            return process(dir ? L + i : L, dir ? R : R - i, i << 1, remain >> 1, !dir);
        }
    }

    public int lastRemaining(int n) {
        return process(1, n, 1, n, true);
    }

}
