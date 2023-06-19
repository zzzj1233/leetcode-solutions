package com.zzzj.lcp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-06-19 17:23
 */
public class LCP12 {

    public static void main(String[] args) {

        System.out.println(minTime(new int[]{1, 2, 3, 3}, 2));

        System.out.println(minTime(LeetUtils.convertInts1("[1,2,7,4,7,7]"), 2));

    }

    public static int minTime(int[] time, int m) {

        int sum = Arrays.stream(time).sum();

        int left = 0;

        int right = sum < 0 ? Integer.MAX_VALUE : sum;

        int ans = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (can(time, m, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    public static boolean can(int[] time, int m, int expect) {

        int sum = 0;

        int day = 0;

        int maxT = 0;

        for (int i = 0; i < time.length; i++) {

            if (day >= m) return false;

            int t = time[i];

            maxT = Math.max(t, maxT);

            if (sum - maxT + t > expect) {
                day++;
                sum = 0;
                maxT = 0;
                i--;
            } else {
                sum += t;
            }

        }

        return true;
    }

}
