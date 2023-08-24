package com.zzzj.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leet2501 {

    public static void main(String[] args) {

        System.out.println(longestSquareStreak(new int[]{4, 3, 6, 16, 8, 2}));

        System.out.println(longestSquareStreak(new int[]{2, 3, 5, 6, 7}));

    }

    public static int longestSquareStreak(int[] nums) {

        int N = nums.length;

        Arrays.sort(nums);

        Map<Integer, Integer> rec = new HashMap<>();

        int ans = 0;

        for (int i = N - 1; i >= 0; i--) {

            int num = nums[i];

            Integer cnt = rec.getOrDefault(num, 1);

            Integer p = rec.get(num * num);

            if (p != null)
                cnt = Math.max(cnt, p + 1);

            rec.put(num, cnt);

            ans = Math.max(ans, cnt);
        }

        return ans == 1 ? -1 : ans;
    }


}
