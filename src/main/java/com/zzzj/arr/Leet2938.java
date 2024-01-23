package com.zzzj.arr;

/**
 * @author zzzj
 * @create 2023-12-28 12:00
 */
public class Leet2938 {

    public static long minimumSteps(String s) {

        int N = s.length();

        long[] preSum = new long[N + 1];

        for (int i = 1; i <= N; i++)
            preSum[i] = preSum[i - 1] + (s.charAt(i - 1) == '1' ? 1 : 0);

        long ans = 0;

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '0')
                ans += preSum[i + 1];
        }

        return ans;
    }

}
