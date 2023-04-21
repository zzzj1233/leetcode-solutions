package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-04-21 16:21
 */
public class Leet2541 {

    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{4, 3, 1, 4}, new int[]{1, 3, 7, 1}, 3));
    }

    public static long minOperations(int[] nums1, int[] nums2, int k) {

        if (Arrays.stream(nums1).sum() != Arrays.stream(nums2).sum()) {
            return -1;
        }

        int N = nums1.length;

        long ans = 0;

        long n1 = 0;

        long n2 = 0;

        for (int i = 0; i < N; i++) {

            if (nums1[i] != nums2[i]) {

                if (k == 0) {
                    return -1;
                }

                if (nums1[i] > nums2[i]) {

                    int sub = nums1[i] - nums2[i];

                    if (sub % k != 0) {
                        return -1;
                    }

                    long step = sub - n1;

                    if (step < 0) {
                        step = 0;
                        n1 -= sub;
                    } else {
                        n2 += sub - n1;
                        n1 = 0;
                    }

                    ans += step / k;

                } else {

                    int sub = nums2[i] - nums1[i];

                    if (sub % k != 0) {
                        return -1;
                    }

                    if (sub % k != 0) {
                        return -1;
                    }

                    long step = sub - n2;

                    if (step < 0) {
                        step = 0;
                        n2 -= sub;
                    } else {
                        n1 += sub - n2;
                        n2 = 0;
                    }

                    ans += step / k;
                }

            }

        }

        return ans;
    }

}
