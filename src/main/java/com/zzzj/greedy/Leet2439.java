package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-02-27 15:59
 */
public class Leet2439 {

    public static void main(String[] args) {
        System.out.println(minimizeArrayValue(new int[]{3, 7, 1, 6}));

        System.out.println(minimizeArrayValue(new int[]{13, 13, 20, 0, 8, 9, 9}));

        System.out.println(minimizeArrayValue(new int[]{1, 10}));
    }

    // 使数组中的最大值最小

    // 1 ~ i ~ n
    // nums[i] - 1
    // nums[i - 1] + 1
    public static int minimizeArrayValue(int[] nums) {

        int min = Arrays.stream(nums).min().getAsInt();

        int max = Arrays.stream(nums).max().getAsInt();

        int ans = -1;

        while (min <= max) {
            int mid = min + ((max - min) >> 1);
            if (check(nums, mid)) {
                ans = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return ans;
    }

    public static boolean check(int[] nums, int expect) {
        int N = nums.length;

        if (nums[0] > expect) {
            return false;
        }

        long reduce = 0;

        for (int i = 0; i < N; i++) {
            if (nums[i] < expect) {
                reduce += expect - nums[i];
            } else if (nums[i] > expect) {
                if (nums[i] - expect > reduce) {
                    return false;
                }
                reduce -= nums[i] - expect;
            }
        }

        return true;
    }

}
