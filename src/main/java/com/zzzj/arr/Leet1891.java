package com.zzzj.arr;

import java.util.Arrays;

public class Leet1891 {

    public static void main(String[] args) {
        System.out.println(maxLength(new int[]{7, 5, 9}, 4));
    }

    public static int maxLength(int[] ribbons, int k) {

        int N = ribbons.length;

        int right = Arrays.stream(ribbons).max().getAsInt();
        int left = 1;
        long sum = Arrays.stream(ribbons).asLongStream().sum();

        if (sum < k) {
            return 0;
        }

        int ret = 0;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(ribbons, k, mid)) {
                ret = mid;
                left = mid + 1;
            } else { // 不满足
                right = mid - 1;
            }
        }

        return ret;
    }

    public static boolean check(int[] ribbons, int k, int expect) {
        int N = ribbons.length;

        int total = 0;

        for (int i = 0; i < N && total < k; i++) {
            total += ribbons[i] / expect;
        }

        return total >= k;
    }

}
