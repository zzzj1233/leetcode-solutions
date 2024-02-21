package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2024-01-26 11:54
 */
public class Leet644 {

    public static void main(String[] args) {

        System.out.println(findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));

    }

    public static double findMaxAverage(int[] nums, int k) {

        int N = nums.length;

        double[] ps = new double[N + 1];

        double left = -1.0e4;

        double right = 1.0e4;

        while (left + 1.0e-6 < right) {

            double mid = left + ((right - left) / 2.0);

            if (check(nums, k, mid, ps)) {
                left = mid;
            } else {
                right = mid;
            }

        }

        return left;
    }

    private static boolean check(
            int[] nums,
            int k,
            double avg,
            double[] ps
    ) {

        int N = nums.length;

        for (int i = 1; i <= N; i++)
            ps[i] = ps[i - 1] + nums[i - 1] - avg;

        double min = ps[0];

        for (int i = k; i <= N; i++) {
            if (ps[i] - min >= 0)
                return true;
            min = Math.min(min, ps[i - k + 1]);
        }

        return false;
    }

}
