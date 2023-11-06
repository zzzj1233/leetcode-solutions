package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-11-01 12:17
 */
public class Leet2321 {

    public static void main(String[] args) {

        System.out.println(maximumsSplicedArray(new int[]{60, 60, 60}, new int[]{10, 90, 10}));

        System.out.println(maximumsSplicedArray(new int[]{20, 40, 20, 70, 30}, new int[]{50, 20, 50, 40, 20}));

        System.out.println(maximumsSplicedArray(new int[]{7, 11, 13}, new int[]{1, 1, 1}));

        System.out.println(maximumsSplicedArray(new int[]{10, 20, 50, 15, 30, 10}, new int[]{40, 20, 10, 100, 10, 10}));

    }

    public static int maximumsSplicedArray(int[] nums1, int[] nums2) {

        int ori = Arrays.stream(nums1).sum();

        int sum = ori;

        int ans = sum;

        int N = nums1.length;

        for (int i = 0; i < N; i++) {

            sum = sum - nums1[i] + nums2[i];

            if (sum < ori)
                sum = ori;

            ans = Math.max(ans, sum);
        }

        ori = Arrays.stream(nums2).sum();

        sum = ori;

        ans = Math.max(ans, ori);

        for (int i = 0; i < N; i++) {

            sum = sum - nums2[i] + nums1[i];

            if (sum < ori)
                sum = ori;

            ans = Math.max(ans, sum);
        }

        return ans;
    }

}
