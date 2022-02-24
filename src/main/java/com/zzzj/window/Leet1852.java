package com.zzzj.window;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-02-24 16:36
 */
public class Leet1852 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(distinctNumbers(new int[]{1, 2, 3, 2, 2, 1, 3}, 3)));
        System.out.println(Arrays.toString(distinctNumbers(new int[]{1, 1, 1, 1, 2, 3, 4}, 4)));
    }

    public static int[] distinctNumbers(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        int[] ans = new int[nums.length - k + 1];

        for (int i = 0; i < k - 1; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int l = 0;
        int r = k - 1;

        while (r < nums.length) {
            int key = nums[r];
            map.put(key, map.getOrDefault(key, 0) + 1);

            ans[l] = map.size();

            int key2 = nums[l];
            Integer count = map.get(key2);
            if (count == 1) {
                map.remove(key2);
            } else {
                map.put(key2, count - 1);
            }
            l++;
            r++;
        }

        return ans;
    }

}
