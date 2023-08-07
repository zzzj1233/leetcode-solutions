package com.zzzj.contest.week336;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-02 16:51
 */
public class Leet2587 {

    public static void main(String[] args) {

        System.out.println(maxScore(new int[]{2, -1, 0, 1, -3, 3, -3}));

        System.out.println(maxScore(new int[]{-2, -3, 0}));

    }

    public static int maxScore(int[] nums) {

        Arrays.sort(nums);

        int N = nums.length;

        long sum = 0;

        int cnt = 0;

        for (int i = N - 1; i >= 0; i--) {
            sum += nums[i];
            if (sum > 0) cnt++;
            else break;
        }

        return cnt;
    }

}
