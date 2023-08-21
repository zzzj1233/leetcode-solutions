package com.zzzj.contest.week331;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-21 17:45
 */
public class Leet2560 {

    public static void main(String[] args) {

        System.out.println(minCapability(new int[]{2, 3, 5, 9}, 2));

        System.out.println(minCapability(new int[]{2, 7, 9, 3, 1}, 2));

    }

    public static int minCapability(int[] nums, int k) {
        int left = 0;
        int right = Arrays.stream(nums).max().orElse(0);

        int ans = 0;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(nums, k, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static boolean check(int[] nums, int k, int expect) {
        int cnt = 0;

        for (int i = 0; i < nums.length && cnt < k; i++) {
            if (nums[i] <= expect) {
                cnt++;
                i++;
            }
        }

        return cnt == k;
    }
}
