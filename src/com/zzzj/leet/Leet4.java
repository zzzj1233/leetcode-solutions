package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-22 16:27
 */
public class Leet4 {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

    // 1 2 3 4
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 寻找两个数组的中位数

        int left = 0;
        int right = 0;

        int mid = (nums1.length + nums2.length) / 2 + 1;

        int i = 0;
        int j = 0;

        while (i + j < mid) {
            left = right;
            if (j >= nums2.length) {
                right = nums1[i];
                i++;
            } else if (i < nums1.length && nums1[i] < nums2[j]) {
                right = nums1[i];
                i++;
            } else {
                right = nums2[j];
                j++;
            }
        }

        return (nums1.length + nums2.length) % 2 == 0 ? ((double) left + right) / 2 : right;
    }

}
