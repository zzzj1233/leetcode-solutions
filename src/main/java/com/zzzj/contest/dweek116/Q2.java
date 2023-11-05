package com.zzzj.contest.dweek116;

public class Q2 {

    public static void main(String[] args) {

        System.out.println(minChanges("1001"));

        System.out.println(minChanges("10"));

        System.out.println(minChanges("0000"));

    }

    public static int minChanges(String s) {

        int N = s.length();

        int ans = 0;

        for (int i = 1; i < N; i += 2) {
            if (s.charAt(i) != s.charAt(i - 1))
                ans++;
        }

        return ans;
    }

}
