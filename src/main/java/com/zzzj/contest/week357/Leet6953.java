package com.zzzj.contest.week357;

import java.util.Arrays;
import java.util.List;

public class Leet6953 {

    public static void main(String[] args) {


        System.out.println(canSplitArray(Arrays.asList(1, 1), 3));

    }

    public static boolean canSplitArray(List<Integer> nums, int m) {

        int N = nums.size();

        if (N < 3) return true;

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + nums.get(i - 1);
        }

        return dfs(nums, m, preSum, 0, N - 1, new Boolean[N][N]);
    }

    public static boolean dfs(List<Integer> nums, int m, int[] preSum, int left, int right, Boolean[][] memo) {

        if (right == left) return true;

        if (left > right) return false;

        if (memo[left][right] != null) return memo[left][right];

        if (preSum[right + 1] - preSum[left] < m) {
            return false;
        }

        for (int i = left; i < right; i++) {

            if (dfs(nums, m, preSum, left, i, memo) && dfs(nums, m, preSum, i + 1, right, memo)) {
                memo[left][right] = true;
                return true;
            }

        }

        memo[left][right] = false;
        return false;
    }

}
