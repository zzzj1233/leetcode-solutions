package com.zzzj.contest.week369;

public class Q2 {

    public static void main(String[] args) {


        System.out.println(minSum(new int[]{10, 4, 24, 2, 17, 10, 0, 29, 3, 0, 19, 7, 24, 17, 30, 7},
                new int[]{1, 0, 13, 0, 0, 28, 0, 23, 24, 13, 4, 30, 8, 4, 0, 1, 29, 25}));

        System.out.println(minSum(new int[]{9, 5}, new int[]{15, 12, 5, 21, 4, 26, 27, 9, 6, 29, 0, 18, 16, 0, 0, 0, 20}));

        System.out.println(minSum(new int[]{3, 2, 0, 1, 0}, new int[]{6, 5, 0}));

        System.out.println(minSum(new int[]{2, 0, 2, 0}, new int[]{1, 4}));

    }

    public static long minSum(int[] nums1, int[] nums2) {

        long s1 = 0;

        int z1 = 0;

        for (int i = 0; i < nums1.length; i++) {
            s1 += nums1[i];
            z1 += nums1[i] == 0 ? 1 : 0;
        }

        long s2 = 0;

        int z2 = 0;

        for (int i = 0; i < nums2.length; i++) {
            s2 += nums2[i];
            z2 += nums2[i] == 0 ? 1 : 0;
        }


        long ans = Math.max(s1 + z1, s2 + z2);

        if (z1 > 0 && z2 > 0)
            return ans;
        if (s1 == s2 && z1 == 0 && z2 == 0)
            return ans;
        if (s1 < s2 && z1 > 0 && s1 + z1 <= s2)
            return ans;
        if (s2 < s1 && z2 > 0 && s2 + z2 <= s1)
            return ans;

        return -1;
    }

}
