package com.zzzj.contest.dweek109;

import java.util.Arrays;

public class Leet6930 {

    public static void main(String[] args) {

        System.out.println(isGood(new int[]{2, 1, 3}));

        System.out.println(isGood(new int[]{1, 3, 3, 2}));

        System.out.println(isGood(new int[]{1, 1}));

        System.out.println(isGood(new int[]{3, 4, 4, 1, 2, 1}));

    }

    public static boolean isGood(int[] nums) {

        if (nums.length == 1) return false;

        Arrays.sort(nums);

        int N = nums.length;

        if (nums[N - 1] != N - 1 || nums[N - 2] != N - 1) return false;

        for (int i = 0; i < N - 2; i++) if (nums[i] != i + 1) return false;

        return true;
    }

}
