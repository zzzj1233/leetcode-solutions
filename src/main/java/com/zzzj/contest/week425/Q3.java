package com.zzzj.contest.week425;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-11-24 10:41
 */
public class Q3 {

    public static void main(String[] args) {

        System.out.println(minArraySum(
                new int[]{2, 8, 3, 19, 3}, 3, 1, 1
        ));

        System.out.println(minArraySum(
                new int[]{2, 4, 3}, 3, 2, 1
        ));

    }

    public static int minArraySum(int[] nums, int k, int op1, int op2) {

        int N = nums.length;

        int[][][] memo = new int[N][op1 + 1][op2 + 1];

        for (int i = 0; i < N; i++)
            for (int j = 0; j <= op1; j++)
                Arrays.fill(memo[i][j], Integer.MAX_VALUE);

        return dfs(nums, 0, k, op1, op2, memo);
    }

    public static int dfs(
            int[] nums,
            int i,
            int k,
            int op1,
            int op2,
            int[][][] memo
    ) {

        if (i >= nums.length)
            return 0;

        if (memo[i][op1][op2] != Integer.MAX_VALUE)
            return memo[i][op1][op2];

        if (op1 == 0 && op2 == 0)
            return nums[i] + dfs(nums, i + 1, k, 0, 0, memo);

        int r = dfs(nums, i + 1, k, op1, op2, memo) + nums[i];

        if (op1 > 0 && op2 > 0) {

            if (nums[i] >= k) {
                int num = nums[i] - k;
                int div = num % 2 == 0 ? num / 2 : num / 2 + 1;
                r = Math.min(
                        r,
                        dfs(nums, i + 1, k, op1 - 1, op2 - 1, memo) + div
                );
            }

            int div = nums[i] % 2 == 0 ? nums[i] / 2 : nums[i] / 2 + 1;

            if (div >= k)
                r = Math.min(
                        r,
                        dfs(nums, i + 1, k, op1 - 1, op2 - 1, memo) + div - k
                );

        }
        if (op1 > 0) {
            int div = nums[i] % 2 == 0 ? nums[i] / 2 : nums[i] / 2 + 1;
            r = Math.min(
                    r,
                    dfs(nums, i + 1, k, op1 - 1, op2, memo) + div
            );
        }
        if (op2 > 0 && nums[i] >= k) {
            r = Math.min(
                    r,
                    dfs(nums, i + 1, k, op1, op2 - 1, memo) + nums[i] - k
            );
        }

        memo[i][op1][op2] = r;

        return r;
    }

}
