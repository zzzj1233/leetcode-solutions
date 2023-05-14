package com.zzzj.arr;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leet822 {


    public static void main(String[] args) {
        System.out.println(flipgame(new int[]{1, 2, 4, 4, 7}, new int[]{1, 3, 4, 1, 3}));
    }

    public static int flipgame(int[] fronts, int[] backs) {

        int N = fronts.length;

        // 1. 背面的数字正面都没有

        // 2. 最小值

        // 3. 可翻转

        Set<Integer> repeated = new HashSet<>();

        for (int i = 0; i < N; i++) {

            if (fronts[i] == backs[i]) {
                repeated.add(fronts[i]);
            }

        }

        if (repeated.size() == N) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if (fronts[i] != backs[i]) {
                min = Math.min(min, Math.min(
                        repeated.contains(fronts[i]) ? Integer.MAX_VALUE : fronts[i]
                        ,
                        repeated.contains(backs[i]) ? Integer.MAX_VALUE : backs[i]
                ));
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

}
