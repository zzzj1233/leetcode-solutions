package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2024-01-22 11:41
 */
public class Leet774 {

    public static void main(String[] args) {

        System.out.println(minmaxGasDist(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9));

        System.out.println(minmaxGasDist(new int[]{23, 24, 36, 39, 46, 56, 57, 65, 84, 98}, 1));

    }

    public static double minmaxGasDist(int[] stations, int k) {

        final double exp = 1e-6;

        double left = 0;

        double right = Math.pow(10, 8);

        double ans = -1;

        while (left + exp < right) {

            double mid = left + ((right - left) / 2.0);

            if (check(stations, k, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        return ans;
    }

    public static boolean check(
            int[] stations,
            int k,
            double expect
    ) {

        int N = stations.length;

        int used = 0;

        for (int i = 1; i < N; i++) {

            int diff = stations[i] - stations[i - 1];

            used += diff / expect;

            if (used > k)
                return false;
        }

        return true;
    }


}
