package com.zzzj.contest.week393;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-06-04 18:52
 */
public class Leet3116 {

    public static void main(String[] args) {

        System.out.println(findKthSmallest(new int[]{9, 6, 3}, 8));
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    // 容斥原理通用模板
    // ∣A∪B∣=∣A∣+∣B∣ - |A∩B|
    // ∣A∪B∪C∣=∣A∣+∣B∣+∣C∣ - ∣A∩B∣−∣A∩C∣−∣B∩C∣ + ∣A∩B∩C∣
    // ∣A∪B∪C∪D∣=∣A∣+∣B∣+∣C∣+∣D∣−∣A∩B∣−∣A∩C∣−∣A∩D∣−∣B∩C∣−∣B∩D∣−∣C∩D∣+∣A∩B∩C∣+∣A∩B∩D∣+∣A∩C∩D∣+∣B∩C∩D∣−∣A∩B∩C∩D∣
    public static long findKthSmallest(int[] coins, int k) {

        long l = k - 1;

        long r = (long) Arrays.stream(coins).min().orElse(0) * k;

        while (l + 1 < r) {

            long m = l + ((r - l) >> 1);

            if (check(m, coins, k))
                r = m;
            else
                l = m;

        }


        return r;
    }

    public static boolean check(
            long expect,
            int[] coins,
            int k
    ) {

        int N = coins.length;

        int limit = 1 << N;

        long c = 0;

        for (int s = 1; s < limit; s++) {

            long lcm = 1;

            for (int i = 0; i < N; i++) {

                if ((s & (1 << i)) != 0)
                    lcm = lcm(lcm, coins[i]);

                if (lcm > expect)
                    break;

            }

            if (Integer.bitCount(s) % 2 == 0)
                c -= expect / lcm;
            else
                c += expect / lcm;
        }

        return c >= k;
    }


}
