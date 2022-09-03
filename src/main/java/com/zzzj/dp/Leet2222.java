package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-08-29 19:16
 */
public class Leet2222 {


    public static long numberOfWays(String s) {
        int N = s.length();

        long ans = 0;

        long zeroCount = s.charAt(0) == '0' ? 1 : 0;
        long oneCount = s.charAt(0) == '1' ? 1 : 0;

        long zoCount = 0;
        long ozCount = 0;

        for (int i = 1; i < N; i++) {
            if (s.charAt(i) == '0') {
                ans += zoCount;
                ozCount += oneCount;
                zeroCount++;
            } else {
                ans += ozCount;
                zoCount += zeroCount;
                oneCount++;
            }
        }

        return ans;
    }

}
