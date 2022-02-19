package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-01-16 21:16
 */
public class Leet7 {

    public static void main(String[] args) {

        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(120));
        System.out.println(reverse(0));
        System.out.println(reverse(1534236469));
    }

    public static int reverse(int x) {

        int ans = 0;

        boolean neg = x < 0;

        int m1 = Integer.MAX_VALUE / 10;
        int n1 = Integer.MAX_VALUE % 10;

        int m2 = Integer.MIN_VALUE / 10;
        int n2 = Integer.MIN_VALUE % 10;

        while (x != 0) {

            if (neg) {
                if (ans < m2 || (ans == m2 && x % 10 < n2)) {
                    return 0;
                }
            } else {
                if (ans > m1 || (ans == m1 && x % 10 > n1)) {
                    return 0;
                }
            }

            ans = ans * 10 + x % 10;
            x /= 10;
        }

        return ans;
    }

}
