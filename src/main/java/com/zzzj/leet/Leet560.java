package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2022-05-22 19:02
 */
public class Leet560 {

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1}, 0));
    }

    public static int subarraySum(int[] nums, int k) {
        int sum = 0;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }

}
