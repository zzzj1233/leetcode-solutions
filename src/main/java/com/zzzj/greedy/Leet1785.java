package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-01-23 18:40
 */
public class Leet1785 {

    public static int minElements(int[] nums, int limit, int goal) {
        long sum = Arrays.stream(nums).asLongStream().sum();

        if (sum == goal) {
            return 0;
        }

        long diff;

        if (sum > goal) {
            diff = sum - goal;
        } else {
            diff = goal - sum;
        }

        return (int) (diff / limit + (diff % limit == 0 ? 0 : 1));
    }

}
