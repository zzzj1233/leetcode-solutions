package com.zzzj.leet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2024-10-19 18:08
 */
public class DifferenceRandomNumber {

    private final Set<Integer> used;

    private final int left;

    private final int right;

    private final int total;

    public DifferenceRandomNumber(int left, int right) {
        this.used = new HashSet<>();
        this.left = left;
        this.right = right;
        this.total = right - left + 1;
    }

    public int nextNum() {

        if (used.size() == total)
            throw new IllegalArgumentException("Illegal range");

        int num;

        while (!used.add((num = LeetUtils.random.nextInt(right) + left))) ;

        return num;
    }

    public int[] nextArr(int N) {
        int[] r = new int[N];

        for (int i = 0; i < N; i++)
            r[i] = nextNum();

        return r;
    }
}
