package com.zzzj.contest.dweek127;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2024-05-23 17:18
 */
public class Leet3098 {

    public static void main(String[] args) {

        System.out.println(sumOfPowers(new int[]{1, 2, 3, 4}, 3));

        System.out.println(sumOfPowers(new int[]{4, 3, -1}, 2));

    }

    static final int MOD = (int) (1e9 + 7);

    public static int sumOfPowers(int[] nums, int K) {
        Arrays.sort(nums);
        return (int) dfs(nums, 0, -1, Integer.MAX_VALUE, K, new HashMap<>());
    }

    public static long dfs(
            int[] nums,
            int i,
            int prev,
            int minDiff,
            int len,
            Map<String, Long> memo
    ) {

        if (len > nums.length - i)
            return 0;

        if (i >= nums.length || len == 0)
            return minDiff;

        String key = i + "#" + prev + "#" + minDiff + "#" + len;

        if (memo.containsKey(key))
            return memo.get(key);

        long res = (dfs(nums, i + 1, i, prev == -1 ? minDiff : Math.min(minDiff, nums[i] - nums[prev]), len - 1, memo) +
                dfs(nums, i + 1, prev, minDiff, len, memo)) % MOD;

        memo.put(key, res);

        return res;
    }
}
