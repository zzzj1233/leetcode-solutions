package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-07-08 18:03
 */
public class Leet801 {

    public static int minSwap(int[] nums1, int[] nums2) {
        return -1;
    }

    public static int dfs(int[] nums1, int[] nums2, int i, int j) {
        if (nums1[i] > nums1[i - 1]) {
            if (nums2[j] > nums2[j - 1]) {
                return dfs(nums1, nums2, i + 1, j + 1);
            }
            return dfs(nums1, nums2, i + 1, j);
        }

        if (nums2[j] > nums2[j - 1]) {
            return dfs(nums1, nums2, i, j + 1);
        }

        // 只能交换i位置的数字
        return -1;
    }

}
