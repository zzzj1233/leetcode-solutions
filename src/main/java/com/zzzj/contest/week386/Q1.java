package com.zzzj.contest.week386;

import java.util.HashMap;
import java.util.Map;

public class Q1 {

    public static void main(String[] args) {

        System.out.println(isPossibleToSplit(new int[]{1, 1, 2, 2, 3, 4}));

        System.out.println(isPossibleToSplit(new int[]{1, 1, 1, 1}));

        System.out.println(isPossibleToSplit(new int[]{10, 1, 7, 4, 5, 1, 6, 4}));

    }

    public static boolean isPossibleToSplit(int[] nums) {

        int N = nums.length;

        Map<Integer, Integer> same = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (!same.containsKey(nums[i])) {
                same.put(nums[i], 1);
            } else {
                Integer old = same.get(nums[i]);

                if (old + 1 > 2)
                    return false;

                same.put(nums[i], 2);
            }

        }

        return true;
    }

}

