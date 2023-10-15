package com.zzzj.contest.week363;

import java.util.Arrays;
import java.util.List;

public class Q1 {

    public static void main(String[] args) {

        System.out.println(sumIndicesWithKSetBits(Arrays.asList(5, 10, 1, 5, 2), 1));

        System.out.println(sumIndicesWithKSetBits(Arrays.asList(4, 3, 2, 1), 2));

    }

    public static int sumIndicesWithKSetBits(List<Integer> nums, int k) {

        int ans = 0;

        for (int i = 0; i < nums.size(); i++) {
            if (Integer.bitCount(i) == k)
                ans += nums.get(i);
        }

        return ans;
    }

}
