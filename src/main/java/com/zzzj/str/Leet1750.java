package com.zzzj.str;

/**
 * @author zzzj
 * @create 2023-06-09 15:56
 */
public class Leet1750 {

    public static void main(String[] args) {

        System.out.println(minimumLength("aabccabba"));

        System.out.println(minimumLength("cabaabac"));

        System.out.println(minimumLength("bbbbbbbbbbbbbbbbbbb"));

    }

    public static int minimumLength(String s) {

        int N = s.length();

        int left = 0;

        int right = N - 1;

        int deleted = 0;

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) return N - deleted;

            char c = s.charAt(left);

            while (left <= right && s.charAt(left) == c) {
                left++;
                deleted++;
            }

            while (right >= left && s.charAt(right) == c) {
                right--;
                deleted++;
            }

        }

        return N - deleted;
    }

}
