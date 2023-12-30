package com.zzzj.contest.week375;

import java.util.Arrays;

public class Leet2962 {

    public static void main(String[] args) {

        System.out.println(countSubarrays(new int[]{1, 3, 2, 3, 3}, 2));

    }

    public static long countSubarrays(int[] nums, int k) {

        int N = nums.length;

        int max = Arrays.stream(nums).max().orElse(0);

        long ans = 0;

        int left = 0;

        int right = 0;

        int maxCnt = 0;

        while (right < N) {

            int num = nums[right];

            if (num == max)
                maxCnt++;

            while (maxCnt == k) {
                ans += N - right;
                num = nums[left];
                if (num == max)
                    maxCnt--;
                left++;
            }

            right++;
        }

        return ans;
    }

}
