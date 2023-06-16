package com.zzzj.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-06-13 11:39
 */
public class Leet2537 {

    public static void main(String[] args) {

        System.out.println(countGood(new int[]{3, 1, 4, 3, 2, 2, 4}, 2));

        System.out.println(countGood(new int[]{1, 1, 1, 1, 1}, 10));

    }

    public static long countGood(int[] nums, int k) {

        int N = nums.length;

        Map<Integer, Integer> cnt = new HashMap<>();

        int left = 0;

        int right = 0;

        int pairs = 0;

        long ans = 0;

        while (right < N) {

            int num = nums[right];

            Integer old = cnt.getOrDefault(num, 0);

            cnt.put(num, old + 1);

            pairs += old;

            while (pairs >= k) {
                ans += N - right;

                Integer leftCnt = cnt.get(nums[left]);

                pairs -= leftCnt - 1;

                cnt.put(nums[left], leftCnt - 1);

                left++;
            }

            right++;
        }

        return ans;
    }

}
