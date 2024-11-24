package com.zzzj.contest.week423;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2024-11-10 10:30
 */
public class Q1 {

    public static void main(String[] args) {

        System.out.println(hasIncreasingSubarrays(
                Arrays.asList(-3, -19, -8, -16),
                3
        ));

        System.out.println(hasIncreasingSubarrays(
                Arrays.asList(2, 5, 7, 8, 9, 2, 3, 4, 3, 1),
                3
        ));

        System.out.println(hasIncreasingSubarrays(
                Arrays.asList(1, 2, 3, 4, 4, 4, 4, 5, 6, 7),
                5
        ));

    }

    public static boolean hasIncreasingSubarrays(List<Integer> nums, int k) {

        int N = nums.size();

        OUTER:
        for (int i = 0; i + k <= N; i++) {

            for (int j = 1; j < k; j++) {
                if (nums.get(j + i) <= nums.get(j + i - 1)) {
                    continue OUTER;
                }
            }

            for (int j = 1; j < k ; j++) {
                if (j + i + k >= N)
                    return false;

                if (nums.get(j + i + k) <= nums.get(j + i + k - 1))
                    continue OUTER;
            }

            return true;

        }

        return false;
    }

}
