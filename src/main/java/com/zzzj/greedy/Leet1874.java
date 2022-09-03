package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-02 15:30
 */
public class Leet1874 {

    public static int minProductSum(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int ans = 0;

        int N = nums1.length;

        int left = 0;
        int right = N - 1;

        while (right >= 0) {
            ans += nums1[left] * nums2[right];
            left++;
            right--;
        }

        return ans;
    }

}
