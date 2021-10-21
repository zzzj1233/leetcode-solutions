package com.zzzj.leet;

import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2021-09-23 11:58
 */
public class Leet220 {

    /**
     * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，
     * 使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
     */
    public static void main(String[] args) {
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }


    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0) {
            return false;
        }

        int i = 0;
        int j = 0;

        TreeSet<Integer> set = new TreeSet();

        while (j < nums.length) {
            int num = nums[j];

            Integer l = set.floor(num);
            Integer r = set.ceiling(num);

            if (l != null && num - l <= t) return true;
            if (r != null && r - num <= t) return true;

            if (j + 1 - i <= k) {
                set.add(nums[j]);
                j++;
            } else {
                set.remove(nums[i]);
                i++;
            }
        }

        return false;
    }

}
