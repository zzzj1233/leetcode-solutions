package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-01-23 20:54
 */
public class Leet1744 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(canEat(
                LeetUtils.convertInts1("[7,4,5,3,8]"),
                LeetUtils.convertInts("[[0,2,2],[4,2,4],[2,13,1000000000]]")
        )));

        System.out.println(Arrays.toString(canEat(
                LeetUtils.convertInts1("[5,2,6,4,1]"),
                LeetUtils.convertInts("[[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]")
        )));
    }

    public static boolean[] canEat(int[] candiesCount, int[][] queries) {

        int N = candiesCount.length;

        int M = queries.length;

        boolean[] ans = new boolean[M];

        long[] preSum = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + candiesCount[i - 1];
        }

        for (int i = 0; i < M; i++) {
            int[] query = queries[i];

            int type = query[0];
            int day = query[1];
            int limit = query[2];

            long sum = preSum[type];

            if (day > preSum[type + 1]) {
                ans[i] = false;
            } else {
                ans[i] = (long) day * limit > sum;
            }
        }

        return ans;
    }


}
