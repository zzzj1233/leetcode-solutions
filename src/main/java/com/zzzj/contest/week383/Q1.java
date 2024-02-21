package com.zzzj.contest.week383;

/**
 * @author zzzj
 * @create 2024-02-04 10:27
 */
public class Q1 {

    public static void main(String[] args) {

        System.out.println(returnToBoundaryCount(new int[]{2, 3, -5}));

        System.out.println(returnToBoundaryCount(new int[]{3, 2, -3, -4}));

    }

    public static int returnToBoundaryCount(int[] nums) {

        int ans = 0;

        int location = 0;

        for (int num : nums) {
            location += num;
            if (location == 0)
                ans++;
        }

        return ans;
    }
}
