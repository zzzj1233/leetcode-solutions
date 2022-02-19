package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-01-15 21:55
 */
public class Leet4 {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println(findMedianSortedArrays(new int[]{0, 0}, new int[]{0, 0}));
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{1}));
        System.out.println(findMedianSortedArrays(new int[]{2}, new int[]{}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int cur = 0;
        int prev = 0;

        int total = nums1.length + nums2.length;

        boolean isOdd = total % 2 == 0;

        int len = total / 2 + 1;
        int i = 0;
        int j = 0;

        int index = 0;

        while (index < len) {

            int val1 = Integer.MAX_VALUE;

            int val2 = Integer.MAX_VALUE;

            if (i < nums1.length) {
                val1 = nums1[i];
            }

            if (j < nums2.length) {
                val2 = nums2[j];
            }

            prev = cur;
            if (val1 < val2) {
                cur = val1;
                i++;
            } else {
                cur = val2;
                j++;
            }

            index++;
        }

        return isOdd ? (cur + prev) / 2.0 : cur;
    }

}
