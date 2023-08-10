package com.zzzj.contest.dweek32;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-08-09 14:22
 */
public class Leet1542 {

    public static void main(String[] args) {

        System.out.println(longestAwesome("3242415"));

        System.out.println(longestAwesome("12345678"));

        System.out.println(longestAwesome("213123"));

        System.out.println(longestAwesome("00"));

    }

    public static int longestAwesome(String s) {

        int N = s.length();

        int[] preSum = new int[N + 1];

        Map<Integer, Integer> rec = new HashMap<>();

        rec.put(0, 0);

        int ans = 0;

        for (int i = 1; i <= N; i++) {

            preSum[i] = preSum[i - 1] ^ (1 << (s.charAt(i - 1) - 'a'));

            Integer old = rec.get(preSum[i]);

            if (old != null) {
                ans = Math.max(ans, i - old);
            }

            for (int j = 0; j < 32; j++) {
                ans = Math.max(ans, i - rec.getOrDefault(preSum[i] ^ (1 << j), i));
            }

            rec.putIfAbsent(preSum[i], i);
        }

        return ans;
    }

}
