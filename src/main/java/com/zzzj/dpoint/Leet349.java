package com.zzzj.dpoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zzzj
 * @create 2021-12-20 22:35
 */
public class Leet349 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[]{};
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        // 两个有序数组的交集
        int j = 0;

        List<Integer> list = new ArrayList<>();

        // 4 5 9
        // 4 4 8 9 9
        for (int i = 0; i < nums1.length && j < nums2.length; ) {
            int n1 = nums1[i];

            int n2 = nums2[j];

            if (n1 > nums2[j]) {
                while (j < nums2.length && nums2[j] == n2) {
                    j++;
                }
            } else if (n1 < nums2[j]) {
                while (i < nums1.length && nums1[i] == n1) {
                    i++;
                }
            } else {
                list.add(n1);
                while (i < nums1.length && nums1[i] == n1) {
                    i++;
                }
                while (j < nums2.length && nums2[j] == n1) {
                    j++;
                }
            }

        }

        int[] ans = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

}
