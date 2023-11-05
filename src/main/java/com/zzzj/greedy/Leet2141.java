package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-24 10:27
 */
public class Leet2141 {

    public static void main(String[] args) {

        System.out.println(maxRunTime(3, new int[]{10, 10, 3, 5}));

    }

    public static long maxRunTime(int n, int[] batteries) {

        Arrays.sort(batteries);

        long left = 0;

        long right = Arrays.stream(batteries).asLongStream().sum();

        long ans = 0;

        while (left <= right) {

            long mid = left + ((right - left) >> 1);

            if (check(n, batteries, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return ans;
    }

    private static boolean check(int n, int[] batteries, long expect) {

        int left = 0;

        int N = batteries.length;

        int right = N - 1;

        int matchIndex = N;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (batteries[mid] >= expect) {
                matchIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        int matchCnt = N - matchIndex;

        int index = 0;
        long curSum = 0;

        while (matchCnt < n && index < matchIndex) {
            curSum += batteries[index];
            if (curSum >= expect) {
                curSum -= expect;
                matchCnt++;
            }
            index++;
        }

        return matchCnt >= n;
    }

}
