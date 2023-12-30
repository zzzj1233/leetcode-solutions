package com.zzzj.contest.week371;

public class Q3 {

    public static void main(String[] args) {

        System.out.println(minOperations(new int[]{1, 2, 7}, new int[]{4, 5, 3}));

        System.out.println(minOperations(new int[]{2, 3, 4, 5, 9}, new int[]{8, 8, 4, 4, 4}));

        System.out.println(minOperations(new int[]{1, 5, 4}, new int[]{2, 5, 3}));

    }

    public static int minOperations(int[] nums1, int[] nums2) {

        int ans;

        int c1 = check(nums1, nums2, nums1[nums1.length - 1], nums2[nums1.length - 1]);

        int c2 = check(nums1, nums2, nums2[nums1.length - 1], nums1[nums1.length - 1]);

        if (c2 != Integer.MAX_VALUE) c2++;

        ans = Math.min(c1, c2);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int check(int[] nums1, int[] nums2, int max1, int max2) {

        int N = nums1.length;

        int cnt = 0;

        for (int i = 0; i < N - 1; i++) {
            if (nums1[i] > max1) {
                if (nums2[i] > max1) return Integer.MAX_VALUE;
                if (nums1[i] > max2) return Integer.MAX_VALUE;
                cnt++;
            } else if (nums2[i] > max2) {
                if (nums1[i] > max2) return Integer.MAX_VALUE;
                if (nums2[i] > max1) return Integer.MAX_VALUE;

                cnt++;
            }
        }

        return cnt;
    }

}
