package com.zzzj.arr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-05-05 16:58
 */
public class Leet1711 {

    public static void main(String[] args) {

    }

    public static int countPairs(int[] deliciousness) {
        int N = deliciousness.length;

        Arrays.sort(deliciousness);

        Map<Integer, Long> rec = new HashMap<>(N);

        long ans = 0;

        int mod = 1000000007;

        for (int i = 0; i < N; i++) {

            int n = deliciousness[i];

            if (isPowerOfTwo(n)) {
                ans += rec.getOrDefault(0, 0L);
            }

            int next = nextPowerOfTwo(n);

            int diff = next - n;

            ans += rec.getOrDefault(diff, 0L);

            ans %= mod;

            rec.put(n, rec.getOrDefault(n, 0L) + 1);
        }

        return (int) ans;
    }

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & n - 1) == 0;
    }

    public static int nextPowerOfTwo(int n) {
        int b = 1;
        while (b <= n) {
            b <<= 1;
        }
        return b;
    }

}
