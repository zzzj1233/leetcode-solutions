package com.zzzj.contest.week341;

public class Leet2645 {

    public static void main(String[] args) {
        System.out.println(addMinimum("aaaabb"));
    }

    public static int addMinimum(String word) {

        int N = word.length();

        if (N == 1) return 2;

        int ans = 0;

        if (word.charAt(0) == 'b') ans += 1;
        else if (word.charAt(0) == 'c') ans += 2;

        for (int i = 0; i < N - 1; i++) {

            char c = word.charAt(i);

            char next = word.charAt(i + 1);

            if (c == 'a') {
                if (next == 'c') ans += 1;
                if (next == 'a') ans += 2;
            } else if (c == 'b') {
                if (next == 'a') ans += 1;
                if (next == 'b') ans += 2;
            } else {
                if (next == 'b') ans += 1;
                if (next == 'c') ans += 2;
            }

        }

        if (word.charAt(N - 1) == 'a')
            return ans + 2;
        else if (word.charAt(N - 1) == 'b')
            return ans + 1;
        else
            return ans;
    }

}
