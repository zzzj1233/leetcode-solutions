package com.zzzj.hot;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-04-08 11:16
 */
public class Leet88 {

    public static void main(String[] args) {

        final int[] num1 = {1, 2, 3, 0, 0, 0};

        merge(num1, 3, new int[]{2, 5, 6}, 3);

        System.out.println(Arrays.toString(num1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // nums1.length > nums2.length
        int i = m - 1;
        int j = n - 1;

        int k = nums1.length - 1;

        while (i >= 0 && j >= 0) {
            int max = 0;
            if (nums1[i] > nums2[j]) {
                max = nums1[i];
                i--;
            } else {
                max = nums2[j];
                j--;
            }
            nums1[k--] = max;
        }

        while (i >= 0) {
            nums1[k--] = nums1[i--];
        }

        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }

    }

}
