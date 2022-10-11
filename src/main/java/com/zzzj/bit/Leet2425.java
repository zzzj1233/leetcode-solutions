package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2022-10-11 14:51
 */
public class Leet2425 {

    public static int xorAllNums(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len1 % 2 == 0 && len2 % 2 == 0) {
            return 0;
        }

        if (len1 % 2 == 0) {
            return xor(nums1);
        }

        if (len2 % 2 == 0) {
            return xor(nums2);
        }

        return xor(nums1) ^ xor(nums2);
    }

    public static int xor(int[] nums) {
        int res = 0;

        for (int num : nums) {
            res ^= num;
        }

        return res;
    }

}
