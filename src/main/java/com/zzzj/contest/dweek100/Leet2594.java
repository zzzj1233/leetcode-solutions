package com.zzzj.contest.dweek100;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-10 12:30
 */
public class Leet2594 {

    public static void main(String[] args) {

        System.out.println(repairCars(new int[]{4, 2, 3, 1}, 10));

        System.out.println(repairCars(new int[]{5, 1, 8}, 6));

        System.out.println(repairCars(new int[]{31, 31, 5, 19, 19, 10, 31, 18, 19, 3, 16, 20, 4, 16, 2, 25, 10, 16, 23, 18, 21, 23, 28, 6, 7, 29, 11, 11, 19, 20, 24, 19, 26, 12, 29, 29, 1, 14, 17, 26, 24, 7, 11, 28, 22, 14, 31, 12, 3, 19, 16, 26, 11}, 736185));

    }

    public static long repairCars(int[] ranks, int cars) {

        long left = 0;

        long right = (long) Arrays.stream(ranks).max().orElse(0) * cars * cars;

        long ans = 0;

        while (left <= right) {

            long mid = left + ((right - left) >> 1);

            if (check(ranks, cars, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private static boolean check(int[] ranks, int cars, long expect) {

        int repair = 0;

        for (int rank : ranks) {
            repair += (int) Math.sqrt(expect / rank);
            if (repair >= cars)
                return true;
        }

        return false;
    }

}
