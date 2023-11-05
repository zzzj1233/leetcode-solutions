package com.zzzj.arr;

import java.util.Arrays;

public class Lint3734 {

    public static void main(String[] args) {

        System.out.println(maxLength(new int[]{9, 4, 1}, 3));

        System.out.println(maxLength(new int[]{1, 2, 3}, 7));

        System.out.println(maxLength(new int[]{1, 2, 3}, 6));
    }

    public static int maxLength(int[] ribbons, int k) {

        int N = ribbons.length;

        int left = 1;

        int right = Arrays.stream(ribbons).max().orElse(0);

        int ans = 0;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (check(ribbons, k, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private static boolean check(int[] ribbons, int k, int expect) {

        int cnt = 0;

        for (int ribbon : ribbons) {
            cnt += ribbon / expect;
            if (cnt >= k)
                return true;
        }

        return false;
    }

}
