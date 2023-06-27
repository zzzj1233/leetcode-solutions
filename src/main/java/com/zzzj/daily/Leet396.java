package com.zzzj.daily;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-04-22 10:09
 */
public class Leet396 {

    public static void main(String[] args) {
        System.out.println(maxRotateFunction(new int[]{4, 3, 2, 6}));
    }

    public static int maxRotateFunction(int[] nums) {

        int N = nums.length;

        int sum = Arrays.stream(nums).sum();

        int pre = 0;

        for (int i = 0; i < N; i++) {
            pre += i * nums[i];
        }

        int ans = pre;

        for (int i = 1; i < N; i++) {
            pre = pre - sum + N * nums[i - 1];
            ans = Math.max(ans, pre);
        }

        return ans;
    }

}
