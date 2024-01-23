package com.zzzj.contest.week380;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-01-15 15:17
 */
public class Q1 {

    public static int maxFrequencyElements(int[] nums) {

        int[] bucket = new int[101];

        for (int num : nums) {
            bucket[num]++;
        }

        int max = Arrays.stream(bucket).max().orElse(0);

        return (int) (Arrays.stream(bucket)
                .filter(value -> value == max)
                .count() * max);
    }

}
