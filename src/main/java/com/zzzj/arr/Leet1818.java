package com.zzzj.arr;

import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2023-01-23 14:53
 */
public class Leet1818 {

    // [1,28,21]
    // [9,21,20]
    public static void main(String[] args) {
        System.out.println(minAbsoluteSumDiff(new int[]{1, 7, 5}, new int[]{2, 3, 5}));

        System.out.println(minAbsoluteSumDiff(new int[]{1, 10, 4, 4, 2, 7}, new int[]{9, 3, 5, 1, 7, 4}));

        System.out.println(minAbsoluteSumDiff(new int[]{1, 28, 21}, new int[]{9, 21, 20}));
    }

    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int num : nums1) {
            set.add(num);
        }

        int N = nums1.length;

        long sum = 0;

        int max = 0;

        for (int i = 0; i < N; i++) {
            int sub = Math.abs(nums1[i] - nums2[i]);
            if (sub > 0) {
                Integer floor = set.floor(nums2[i]);
                Integer ceil = set.ceiling(nums2[i]);
                if (floor != null) {
                    max = Math.max(max, sub - (nums2[i] - floor));
                }
                if (ceil != null) {
                    max = Math.max(max, sub - (ceil - nums2[i]));
                }
            }
            sum += sub;
        }

        sum -= max;

        return (int) (sum % 1000000007);
    }

}
