package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-04-21 17:24
 */
public class Leet2517 {

    public static void main(String[] args) {
        System.out.println(maximumTastiness(new int[]{1, 2, 5, 8, 13, 21}, 3));
    }

    // 1, 2, 5, 8, 13, 21
    public static int maximumTastiness(int[] price, int k) {

        Arrays.sort(price);

        int left = 0;
        int right = price[price.length - 1];

        int result = 0;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (can(price, k, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public static boolean can(int[] price, int k, int expect) {
        int N = price.length;

        int cnt = 1;

        int prev = price[0];

        for (int i = 1; i < N; i++) {
            if (price[i] - prev >= expect) {
                cnt++;
                prev = price[i];
            }
        }

        return cnt >= k;
    }

}
