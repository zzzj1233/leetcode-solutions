package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-21 18:11
 */
public class Leet849 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(100, 0, 2);
            arr[LeetUtils.random.nextInt(arr.length)] = 1;
            if (doublePoint(arr) != maxDistToClosest(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    // 双指针
    public static int doublePoint(int[] seats) {
        int left = 0;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                left = i;
                break;
            }
        }

        // 第一个答案
        int ans = left;

        int right = left;

        for (int i = left + 1; i < seats.length; i++) {
            if (seats[i] == 1) {
                left = i;
                continue;
            }

            // 1. 不能越界
            // 2. 在i的左边 || 位置是空的
            while (right < seats.length && (right <= i || seats[right] == 0)) {
                right++;
            }

            ans = Math.max(ans, Math.min(i - left, right - i));
        }

        // 最后一个right的答案
        ans = Math.max(ans, seats.length - left - 1);

        return ans;
    }

    // 前缀数组
    public static int maxDistToClosest(int[] seats) {
        int n = seats.length;

        // left -> right,连续的空座位最长有多少个
        int[] left = new int[n];

        // right -> left,连续的空座位最长有多少个
        int[] right = new int[n];

        left[0] = seats[0] == 0 ? 1 : 0;

        for (int i = 1; i < seats.length; i++) {
            if (seats[i] == 0) {
                left[i] = left[i - 1] + 1;
            }
        }

        right[n - 1] = seats[n - 1] == 0 ? 1 : 0;

        for (int i = n - 2; i >= 0; i--) {
            if (seats[i] == 0) {
                right[i] = right[i + 1] + 1;
            }
        }

        int ans = 0;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                ans = Math.max(ans, Math.min(left[i], right[i]));
            }
        }

        ans = Math.max(ans, right[0]);
        ans = Math.max(ans, left[n - 1]);

        return ans;
    }

}
