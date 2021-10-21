package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2021-09-22 14:07
 */
public class Leet1 {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            Integer index = map.get(target - nums[i]);
            if (index != null && index != i) {
                return new int[]{i, index};
            }
        }

        return null;
    }

}
