package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-07 20:08
 */
public class Leet1109 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(corpFlightBookings(LeetUtils.convertInts("[[1,2,10],[2,3,20],[2,5,25]]"), 5)));
    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];

        // 差分数组
        int[] diff = new int[n + 2];

        for (int[] booking : bookings) {
            int start = booking[0];
            int end = booking[1];
            int count = booking[2];

            diff[start] += count;
            diff[end + 1] -= count;
        }

        for (int i = 1; i < diff.length - 1; i++) {
            ans[i - 1] = diff[i] + (i - 2 >= 0 ? ans[i - 2] : 0);
        }

        return ans;
    }

}
