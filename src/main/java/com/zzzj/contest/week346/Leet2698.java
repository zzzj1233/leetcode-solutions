package com.zzzj.contest.week346;

/**
 * @author zzzj
 * @create 2023-08-01 12:08
 */
public class Leet2698 {

    public static void main(String[] args) {

        System.out.println(punishmentNumber(1000));
    }


    public static int punishmentNumber(int n) {

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            ans += bitSum(i);
        }

        return ans;
    }

    public static int bitSum(int n) {
        int n2 = n * n;

        return canSep(String.valueOf(n2), 0, n) ? n2 : 0;
    }

    public static boolean canSep(String value, int index, int expect) {

        if (index >= value.length()) return expect == 0;

        int sum = 0;

        for (int i = index; i < value.length(); i++) {

            int num = Character.digit(value.charAt(i), 10);

            sum = sum * 10 + num;

            if (sum > expect) break;

            if (canSep(value, i + 1, expect - sum)) {
                return true;
            }

        }

        return false;
    }

}
