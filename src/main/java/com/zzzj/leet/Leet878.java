package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2024-05-31 17:43
 */
public class Leet878 {

    public static void main(String[] args) {

//        System.out.println(
//                check(27473333563L, lcm(2054, 317), 2054, 317)
//        );

        System.out.println(nthMagicalNumber(100000000, 2054, 317));

    }

    public static int nthMagicalNumber(int n, int a, int b) {

        long lcm = lcm(a, b);

        long left = 1;

        long right = (long) Math.min(a, b) * n;

        long mod = (long) (1e9 + 7);

        while (left < right) {
            long m = left + ((right - left) >> 1);
            long c = check(m, lcm, a, b);
            if (c < n)
                left = m + 1;
            else
                right = m;
        }

        return (int) (left % mod);
    }

    public static long check(long num, long lcm, long a, long b) {

        long cnt = num / a + num / b;

        long rep = num / lcm;

        return cnt - rep;
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

}
