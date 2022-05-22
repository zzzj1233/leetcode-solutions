package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2022-05-22 14:23
 */
public class Leet494 {

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{43, 1, 49, 22, 41, 1, 11, 1, 24, 10, 26, 49, 33, 4, 20, 19, 44, 42, 2, 37}, 17));
    }

    public static int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, 0, target, new HashMap<>(nums.length));
    }


    public static int dfs(int[] nums, int index, int rest, Map<int[], Integer> cache) {
        if (index == nums.length) {
            return rest == 0 ? 1 : 0;
        }
        int[] key = {index, rest};

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int result = dfs(nums, index + 1, rest + nums[index], cache) + dfs(nums, index + 1, rest - nums[index], cache);
        cache.put(key, rest);
        return result;
    }

    public static int dp(int[] nums, int target) {
        int N = nums.length;

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        int[][] dp = new int[N + 1][(sum << 1) + 1];
        dp[N][sum] = 1;

        for (int i = N - 1; i >= 0; i--) {

            for (int j = -sum; j <= sum; j++) {
                dp[i][j] = dp[i + 1][sum + j + nums[i]] + dp[i + 1][sum + j - nums[i]];
            }


        }

        return dp[0][sum + target];
    }

}
