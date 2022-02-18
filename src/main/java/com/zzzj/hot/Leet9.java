package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-01-17 10:34
 */
public class Leet9 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(10));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        return x == reverse(x);
    }

    public static int reverse(int x) {

        int ans = 0;

        int m1 = Integer.MAX_VALUE / 10;
        int n1 = Integer.MAX_VALUE % 10;

        while (x != 0) {
            if (ans > m1 || (ans == m1 && x % 10 > n1)) {
                return 0;
            }
            ans = ans * 10 + x % 10;
            x /= 10;
        }

        return ans;
    }

}
