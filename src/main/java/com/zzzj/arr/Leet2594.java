package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2023-04-20 18:10
 */
public class Leet2594 {


    public static void main(String[] args) {

        System.out.println(repairCars(new int[]{4, 2, 3, 1}, 10));

        System.out.println(repairCars(new int[]{5, 1, 8}, 6));

    }

    public static long repairCars(int[] ranks, int cars) {
        long left = 1;

        long right = 100L * cars * cars;

        long ans = -1;

        while (left <= right) {

            long mid = left + ((right - left) >> 1);

            if (can(ranks, cars, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    public static boolean can(int[] ranks, int cars, long expectMinute) {

        int can = 0;

        for (int i = 0; i < ranks.length; i++) {
            int r = ranks[i];

            // r * car * car
            //
            long c = expectMinute / r;

            if (c > 0) {
                can += (int) Math.sqrt(c);

                if (can >= cars) return true;
            }

        }

        return false;
    }


}
