package com.zzzj.contest.week372;

public class Q1 {

    public static void main(String[] args) {

        System.out.println(findMinimumOperations("abc", "abb", "abd"));

    }

    public static int findMinimumOperations(String s1, String s2, String s3) {

        int min = Math.min(
                s1.length(),
                Math.min(
                        s2.length(),
                        s3.length()
                )
        );

        int max = Math.max(
                s1.length(),
                Math.max(
                        s2.length(),
                        s3.length()
                )
        );

        for (int i = 0; i < min; i++) {

            if (s1.charAt(i) != s2.charAt(i) || s1.charAt(i) != s3.charAt(i)) {
                return i >= 2 ? (s1.length() - i) + (s2.length() - i) + (s3.length() - i) : -1;
            }

        }

        if (min == max)
            return 0;

        return  (s1.length() - min) + (s2.length() - min) + (s3.length() - min);
    }

}
