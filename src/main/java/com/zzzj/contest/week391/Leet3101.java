package com.zzzj.contest.week391;

/**
 * @author zzzj
 * @create 2024-05-23 15:36
 */
public class Leet3101 {

    public static void main(String[] args) {

        System.out.println(countAlternatingSubarrays(new int[]{0, 1, 1, 1}));

        System.out.println(countAlternatingSubarrays(new int[]{1, 0, 1, 0}));

    }

    public static long countAlternatingSubarrays(int[] nums) {

        long ans = 0;

        int N = nums.length;

        int left = 0;

        for (int i = 1; i < N; i++) {

            if (nums[i] == nums[i - 1]) {
                ans += (long) (i - left) * (i - left + 1) / 2;
                left = i;
            }

        }

        ans += (long) (N - left) * (N - left + 1) / 2;

        return ans;
    }

}
