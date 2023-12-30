package com.zzzj.contest.dweek115.week239;

/**
 * @author zzzj
 * @create 2023-07-18 18:06
 */
public class Leet1849 {

    public static void main(String[] args) {
//
        System.out.println(splitString("1234"));

        System.out.println(splitString("050043"));

        System.out.println(splitString("9080701"));

        System.out.println(splitString("10009998"));
//
        System.out.println(splitString("001"));

        System.out.println(splitString("64424509442147483647"));

    }

    public static boolean splitString(String s) {
        return dfs(s, 0, null, 0);
    }

    public static long num(String s, int left, int right) {

        long num = 0;

        while (left <= right) {
            char c = s.charAt(left);

            int digit = Character.digit(c, 10);

            num = num * 10 + digit;

            left++;
        }

        return num;
    }

    public static boolean dfs(String s, int left, Long expect, int cnt) {

        if (left >= s.length()) return cnt > 1;

        for (int i = left; i < s.length(); i++) {
            long num = num(s, left, i);
            if (expect == null || num == expect)
                if (dfs(s, i + 1, num - 1, cnt + 1)) {
                    return true;
                }
        }

        return false;
    }


}
