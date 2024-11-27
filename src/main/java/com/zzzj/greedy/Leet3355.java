package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2024-11-24 18:56
 */
public enum Leet3355 {
    ;

    public static void main(String[] args) {

    }

    public static boolean isZeroArray(int[] nums, int[][] queries) {

        int N = nums.length;

        int M = queries.length;

        int[] diff = new int[N + 1];

        for (int[] query : queries) {
            diff[query[0]]++;
            diff[query[1] + 1]--;
        }

        int cur = 0;

        for (int i = 0; i < N; i++) {
            cur += diff[i];

            if (cur < nums[i])
                return false;

        }

        return true;
    }

}
