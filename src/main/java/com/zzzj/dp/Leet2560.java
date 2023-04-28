package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-04-25 17:42
 */
public class Leet2560 {

    public static void main(String[] args) {
        System.out.println(minCapability(new int[]{2, 3, 5, 9}, 2));

        System.out.println(minCapability(new int[]{2, 7, 9, 3, 1}, 2));
    }

    public static int minCapability(int[] nums, int k) {

        int left = Arrays.stream(nums).min().getAsInt();

        int right = Arrays.stream(nums).max().getAsInt();

        int ans = 0;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if (can(nums, k, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    public static boolean can(int[] nums, int k, int expect) {
        int N = nums.length;

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if (nums[i] <= expect) {
                cnt++;
                i++;
            }
        }

        return cnt >= k;
    }

}
