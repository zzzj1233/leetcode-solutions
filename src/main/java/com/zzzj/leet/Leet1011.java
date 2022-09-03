package com.zzzj.leet;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-02 16:08
 */
public class Leet1011 {

    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
        System.out.println(shipWithinDays(new int[]{3, 2, 2, 4, 1, 4}, 3));
        System.out.println(shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4));
    }

    public static int shipWithinDays(int[] weights, int days) {
        int N = weights.length;

        int max = Arrays.stream(weights).max().orElse(0);
        int sum = Arrays.stream(weights).sum();

        // left拿max
        // right拿sum
        int left = max;
        int right = sum;

        while (left < right) {
            int mid = left + ((right - left) >> 1);
            int curSum = 0;
            int curDay = 0;
            for (int i = 0; i < N; i++) {
                curSum += weights[i];
                if (curSum > mid) {
                    curDay++;
                    curSum = weights[i];
                }
            }
            if (curDay + 1 <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

}
