package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2024-06-05 15:36
 */
public class Leet1201 {

    public static void main(String[] args) {

        System.out.println(nthUglyNumber(4, 2, 3, 4));

        System.out.println(nthUglyNumber(5, 2, 11, 13));

    }

    public static int nthUglyNumber(int n, int a, int b, int c) {

        int l = 0;

        int r = Math.min(
                a,
                Math.min(b, c)
        ) * n;

        while (l + 1 < r) {
            int m = l + ((r - l) >> 1);
            if (check(n, a, b, c, m))
                r = m;
            else
                l = m;
        }

        return r;
    }

    public static boolean check(
            int n, int a, int b, int c, int m
    ) {

        // a + b + c - aub - auc + aubuc
        long ab = lcm(a, b);

        long bc = lcm(b, c);

        long ac = lcm(a, c);

        return m / a + m / b + m / c - m / ab - m / bc - m / ac + m / lcm(lcm(a, b), c) >= n;
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

}
