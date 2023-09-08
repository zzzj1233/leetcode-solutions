package com.zzzj.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2023-09-05 18:46
 */
public class Leet2448 {

    private static final int NUM = 0;

    private static final int COST = 1;

    public static long minCost(int[] nums, int[] cost) {

        int N = nums.length;

        int[][] combined = new int[N][2];

        for (int i = 0; i < N; i++) {
            combined[i][NUM] = nums[i];
            combined[i][COST] = cost[i];
        }

        Arrays.sort(combined, Comparator.comparingInt(o -> o[0]));

        int num = combined[0][NUM];

        long costSum = 0;

        for (int i = 1; i < N; i++) {
            costSum += (combined[i][NUM] - num) * combined[i][COST];
        }

        return -1;
    }

}
