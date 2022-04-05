package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-04-02 15:32
 */
public class Leet4 {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{3, 4, 5, 6}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int N = nums1.length + nums2.length;

        int mid = N / 2 + 1;

        int n1 = 0;
        int n2 = 0;

        int m1 = 0;
        int m2 = 0;

        for (int i = 0; i < mid; i++) {
            //
            m2 = m1;
            if (n1 >= nums1.length) {
                m1 = nums2[n2++];
            } else if (n2 >= nums2.length) {
                m1 = nums1[n1++];
            } else {
                if (nums1[n1] > nums2[n2]) {
                    m1 = nums2[n2++];
                } else {
                    m1 = nums1[n1++];
                }
            }
        }

        return N % 2 == 0 ? (m1 + m2) / 2.0 : m1;
    }

}
