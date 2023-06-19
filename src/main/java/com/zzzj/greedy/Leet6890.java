package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-06-19 11:18
 */
public class Leet6890 {

    public static int findValueOfPartition(int[] nums) {

        int N = nums.length;

        // min ( | max(num1) - min(num2) | )

        Arrays.sort(nums);

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < N; i++) {
            ans = Math.min(ans, nums[i] - nums[i - 1]);
        }

        return ans;
    }

}
