package com.zzzj.leet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2021-09-23 10:19
 */
public class Leet219 {

    /**
     * 给定一个整数数组和一个整数k，判断数组中是否存在两个不同的索引i和j，使得nums [i] = nums [j]，并且 i 和 j的差的 绝对值 至多为 k。
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k == 0) {
            return false;
        }

        int i = 0;
        int j = 0;

        Set<Integer> set = new HashSet<>(k);

        while (j < nums.length) {
            if (set.contains(nums[j])) {
                return true;
            }
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

    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }

}
