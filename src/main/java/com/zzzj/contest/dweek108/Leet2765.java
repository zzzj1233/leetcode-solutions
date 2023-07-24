package com.zzzj.contest.dweek108;

import java.security.AlgorithmConstraints;
import java.util.Currency;

/**
 * @author zzzj
 * @create 2023-07-17 17:31
 */
public class Leet2765 {

    public static void main(String[] args) {

        System.out.println(alternatingSubarray(new int[]{2, 3, 4, 3, 4}));

        System.out.println(alternatingSubarray(new int[]{4, 5, 6}));

        System.out.println(alternatingSubarray(new int[]{42, 43, 44, 43, 44, 43, 44, 45, 46}));

        System.out.println(alternatingSubarray(new int[]{31, 32, 31, 32, 33}));

    }

    public static int alternatingSubarray(int[] nums) {

        int N = nums.length;

        int ans = 1;

        for (int i = 0; i < N; i++) {

            int cur = 1;

            boolean add = true;

            for (int j = i + 1; j < N; j++) {

                if (nums[j] == nums[j - 1] + (add ? 1 : -1)) {
                    cur += 1;
                    add = !add;
                } else break;

            }

            ans = Math.max(ans, cur);
        }

        return ans == 1 ? -1 : ans;
    }

}
