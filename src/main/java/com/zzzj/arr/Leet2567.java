package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-04 16:45
 */
public class Leet2567 {

    public static int minimizeSum(int[] nums) {

        int N = nums.length;

        if (N <= 3) return 0;

        Arrays.sort(nums);

        return Math.min(
                nums[N - 1] - nums[2],
                Math.min(
                        nums[N - 2] - nums[1],
                        nums[N - 3] - nums[0]
                )
        );
    }

}
