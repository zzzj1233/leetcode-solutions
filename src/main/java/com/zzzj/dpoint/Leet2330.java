package com.zzzj.dpoint;

/**
 * @author zzzj
 * @create 2022-10-11 11:18
 */
public class Leet2330 {

    public static boolean makePalindrome(String s) {
        // 1 ~ 2 步使字符串变成回文串

        int N = s.length();

        int left, right;

        if (N % 2 == 0) {
            right = N / 2;
            left = right - 1;
        } else {
            left = N / 2 - 1;
            right = left + 2;
        }

        int change = 2;

        while (left >= 0) {
            if (s.charAt(left) != s.charAt(right)) {
                if (change == 0) {
                    return false;
                }
                change--;
            }
            left--;
            right++;
        }

        return true;
    }


}
