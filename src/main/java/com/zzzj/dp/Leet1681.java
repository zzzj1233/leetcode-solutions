package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-06-28 11:05
 */
public class Leet1681 {

    public static void main(String[] args) {

        System.out.println(minimumIncompatibility(new int[]{1, 2, 1, 4}, 2));

        System.out.println(minimumIncompatibility(new int[]{6, 3, 8, 1, 3, 1, 2, 2}, 4));

        System.out.println(minimumIncompatibility(new int[]{5, 3, 3, 6, 3, 3}, 3));

    }

    public static int minimumIncompatibility(int[] nums, int k) {

        int N = nums.length;

        int M = N / k;

        Arrays.sort(nums);

        int[] memo = new int[1 << N];

        Arrays.fill(memo, -1);

        // 划分为k个数组, 每个数组有M个元素
        int ans = dfs(nums, M, (1 << N) - 1, memo);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int dfs(int[] nums, int M, int stat, int[] memo) {

        if (memo[stat] != -1) return memo[stat];

        if (Integer.bitCount(stat) == 0) return 0;

        int res = Integer.MAX_VALUE;

        for (int i = stat; Integer.bitCount(i) > 0; i = (i - 1) & stat) {

            if (Integer.bitCount(i) == M && check(i, nums)) {

                int incompatible = incompatible(i, nums);

                int dfsRes = dfs(nums, M, stat ^ i, memo);

                if (dfsRes != Integer.MAX_VALUE) {
                    res = Math.min(res, dfsRes + incompatible);
                }

            }

        }

        memo[stat] = res;

        return res;
    }

    public static boolean check(int stat, int[] nums) {
        for (int i = 0; i < nums.length; i++)
            for (int j = 0; j < nums.length; j++)
                if (i == j)
                    continue;
                else if ((stat & (1 << i)) != 0 && (stat & (1 << j)) != 0 && nums[i] == nums[j])
                    return false;

        return true;
    }

    public static int incompatible(int stat, int[] nums) {

        int left = 0;

        while (left <= 16) {
            if ((stat & (1 << left)) != 0) break;
            left++;
        }

        int right = nums.length - 1;

        while (right >= 0) {
            if ((stat & (1 << right)) != 0) break;
            right--;
        }

        return nums[right] - nums[left];
    }
}
