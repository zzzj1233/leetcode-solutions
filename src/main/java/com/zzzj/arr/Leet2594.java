package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-04-20 18:10
 */
public class Leet2594 {

    public static long repairCars(int[] ranks, int cars) {
        int left = 0;

        int max = Arrays.stream(ranks).max().getAsInt() * cars * cars;

        int right = max < 0 ? Integer.MAX_VALUE : max;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

        }

        return -1;
    }

    public static boolean can(int[] ranks, int cars, int expectMinute) {

        return false;
    }

    public static int maxRepairCar(int rank, int cars, int expectMinute) {
        int left = 0;

        int right = cars;

        int result = 0;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if (mid * mid * rank <= expectMinute) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return result;
    }

}
