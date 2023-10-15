package com.zzzj.contest.week361;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q3 {

    public static long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();

        long ans = 0;

        Map<Integer, Integer> countMap = new HashMap<>();

        countMap.put(0, 1); // 初始化哈希表，表示前缀和余数为0的情况

        int cnt = 0;

        long sum = 0;

        for (int num : nums) {

            cnt += num % modulo == k ? 1 : 0;

            int mod = cnt % modulo;

            ans += countMap.getOrDefault(Math.abs(k - mod), 0);

            countMap.put(cnt, countMap.getOrDefault(cnt, 0) + 1);

        }

        return ans;
    }

    public static void main(String[] args) {

        // 3
//        System.out.println(countInterestingSubarrays(Arrays.asList(3, 2, 4), 2, 1));
//
//        // 2
//        // [3,1,9,0]
//        // [1]
//        System.out.println(countInterestingSubarrays(Arrays.asList(3, 1, 9, 6), 3, 0));
//
//        // [11,12,21,31]
//        // 10
//        // 1
//        // === 5
//        System.out.println(countInterestingSubarrays(Arrays.asList(11, 12, 21, 31), 10, 1));

        System.out.println(countInterestingSubarrays(Arrays.asList(2, 4), 7, 2));

    }

}
