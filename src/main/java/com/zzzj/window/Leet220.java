package com.zzzj.window;

import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2022-01-05 12:11
 */
public class Leet220 {

    public static void main(String[] args) {
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1}, 1, 2));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // set中维护一个k大小的窗口
        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        if (Math.abs(map.lastKey() - map.firstKey()) <= t) {
            return true;
        }

        int l = 0;
        int r = k;

        while (r < nums.length) {
            if (Math.abs(map.lastKey() - map.firstKey()) <= t) {
                return true;
            }
            Integer count = map.get(nums[l]);
            if (count == 1) {
                map.remove(nums[l]);
            } else {
                map.put(nums[l], count - 1);
            }
            r++;
            l++;
        }

        return false;
    }

}
