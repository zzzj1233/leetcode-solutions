package com.zzzj.contest.dweek98;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-04 16:12
 */
public class Leet2568 {

    public static void main(String[] args) {

        System.out.println(minImpossibleOR(new int[]{2, 1}));

        System.out.println(minImpossibleOR(new int[]{5, 3, 2}));

        System.out.println(minImpossibleOR(new int[]{1, 25, 2, 72}));

        System.out.println(minImpossibleOR(new int[]{4, 32, 16, 8, 8, 75, 1, 2}));

    }

    public static int minImpossibleOR(int[] nums) {

        Arrays.sort(nums);

        int prev = 0;

        int N = nums.length;

        int xor = 0;

        for (int i = 0; i < N; i++) {
            if (i - 1 > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] != xor + 1) {
                return xor + 1;
            }
            xor |= nums[i];
        }

        return xor + 1;
    }


}
