package com.zzzj.dp;


import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2021-11-05 11:06
 */
public class Leet1218 {

    public static int longestSubsequence(int[] arr, int difference) {

        Map<Integer, Integer> dp = new HashMap<>();

        int N = arr.length;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int num = arr[i];
            int v = dp.getOrDefault(num - difference, 0) + 1;
            dp.put(num, v);
            ans = Math.max(ans, v);
        }

        return ans;
    }


}
