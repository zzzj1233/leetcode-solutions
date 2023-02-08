package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-01-23 16:45
 */
public class Leet1775 {

    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 1, 2, 2, 2, 2}));

        System.out.println(minOperations(new int[]{6, 6}, new int[]{1}));

        System.out.println(minOperations(
                new int[]{6, 3, 3, 1, 4, 5, 3, 4, 1, 3, 4},
                new int[]{5, 6, 4, 3, 1, 2})
        );
    }

    // [5,6,4,3,1,2]
    // [6,3,3,1,4,5,3,4,1,3,4]
    public static int minOperations(int[] nums1, int[] nums2) {

        int N = nums1.length;

        int M = nums2.length;

        if (N > M * 6 || M > N * 6) {
            return -1;
        }

        int s1 = Arrays.stream(nums1).sum();

        int s2 = Arrays.stream(nums2).sum();

        if (s1 == s2) {
            return 0;
        }

        if (s1 < s2) {
            return minOperations(nums2, nums1);
        }

        // 保证s1一定>s2

        int[] tb1 = new int[6];

        int[] tb2 = new int[6];

        for (int i = 0; i < N; i++) {
            tb1[nums1[i] - 1]++;
        }

        for (int i = 0; i < M; i++) {
            tb2[6 - nums2[i]]++;
        }

        int ans = 0;

        int sub = s1 - s2;


        for (int i = 5; i > 0; i--) {
            int c1 = tb1[i];
            int c2 = tb2[i];

            if (c1 == 0 && c2 == 0) {
                continue;
            }

            if (sub < i) {
                return ans + 1;
            }

            int count = sub / i + (sub % i == 0 ? 0 : 1);

            if (c1 + c2 >= count) {
                return ans + count;
            }

            ans += c1 + c2;
            sub -= (c1 + c2) * i;
        }


        return ans;
    }


}
