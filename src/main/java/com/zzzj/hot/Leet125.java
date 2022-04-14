package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-04-14 16:04
 */
public class Leet125 {

    public static void main(String[] args) {
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome("0P"));
    }

    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();

        int i = 0;
        int j = chars.length - 1;

        while (i < j) {
            // 97 ~ 122
            while (i < j && !isLower(chars[i]) && !isUpper(chars[i]) && !isDigit(chars[i])) {
                i++;
            }

            while (j > i && !isLower(chars[j]) && !isUpper(chars[j]) && !isDigit(chars[j])) {
                j--;
            }

            if (i == j) {
                return true;
            }

            if (chars[i] != chars[j]) {
                if (isLower(chars[i])) {
                    if (chars[i] - 32 != chars[j]) {
                        return false;
                    }
                } else if (isDigit(chars[i]) || isDigit(chars[j])) {
                    return false;
                } else {
                    if (chars[i] + 32 != chars[j]) {
                        return false;
                    }
                }
            }

            i++;
            j--;
        }

        return true;
    }

    public static boolean isLower(char c) {
        return c >= 97 && c <= 122;
    }

    public static boolean isUpper(char c) {
        return c >= 65 && c <= 90;
    }

    public static boolean isDigit(char c) {
        return c >= 48 && c <= 57;
    }

}
