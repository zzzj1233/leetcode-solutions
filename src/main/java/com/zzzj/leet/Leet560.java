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

        Map<Integer, Integer> rec = new HashMap<>();

        rec.put(0, 1);

        int sum = 0;

        int N = nums.length;

        int ans = 0;

        for (int i = 0; i < N; i++) {

            sum += nums[i];

            ans += rec.getOrDefault(sum - k, 0);

            rec.put(sum, rec.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }

}
