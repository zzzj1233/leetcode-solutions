package com.zzzj.contest.week349;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-25 17:46
 */
public class Leet2735 {

    public static void main(String[] args) {

        System.out.println(minCost(new int[]{20, 1, 15}, 5));

        System.out.println(minCost(new int[]{15, 150, 56, 69, 214, 203}, 42));

    }

    public static long minCost(int[] nums, int x) {

        int N = nums.length;

        int[] cost = Arrays.copyOfRange(nums, 0, nums.length);

        long ans = Arrays.stream(cost).asLongStream().sum();

        // 枚举所有的操作步骤
        for (int i = 1; i < N; i++) {

            // i = 操作几次
            for (int j = 0; j < N; j++) {
                cost[(i + j) % N] = Math.min(
                        cost[(i + j) % N],
                        nums[j]
                );
            }

            ans = Math.min(ans, Arrays.stream(cost).asLongStream().sum() + ((long) i * x));
        }

        return ans;
    }

}
