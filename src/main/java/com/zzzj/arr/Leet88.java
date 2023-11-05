package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2021-12-20 21:43
 */
public class Leet88 {

    public static void main(String[] args) {
        int[] ints = {3, 4, 5, 6, 0, 0, 0};

        merge(ints, 4, new int[]{1, 2, 3}, 3);

        System.out.println(Arrays.toString(ints));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int j = n - 1;

        int i = m - 1;
        //
        //   i
        // 1 2 6 _ _ _
        // 3 4 5

        int tail = nums1.length - 1;


        while (j >= 0) {
            if (i < 0) {
                while (tail >= 0) {
                    nums1[tail] = nums2[j];
                    j--;
                    tail--;
                }
                break;
            }
            int n1 = nums1[i];
            int n2 = nums2[j];
            if (n1 >= n2) {
                nums1[tail] = n1;
                i--;
            } else {
                nums1[tail] = n2;
                j--;
            }
            tail--;
        }

    }

}
