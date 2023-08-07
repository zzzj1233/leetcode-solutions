package com.zzzj.contest.dweek103;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-02 16:15
 */
public class Leet2656 {

    public static void main(String[] args) {

        System.out.println(maximizeSum(new int[]{1, 2, 3, 4, 5}, 3));

        System.out.println(maximizeSum(new int[]{5, 5, 5}, 2));

    }

    public static int maximizeSum(int[] nums, int k) {

        Arrays.sort(nums);

        int ans = 0;

        while (k > 0) {
            ans += nums[nums.length - 1];
            nums[nums.length - 1]++;
            k--;
        }

        return ans;
    }

}
