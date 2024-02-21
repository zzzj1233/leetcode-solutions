package com.zzzj.contest.week383;

/**
 * @author zzzj
 * @create 2024-02-04 10:27
 */
public class Q2 {

    public static void main(String[] args) {

        System.out.println(minimumTimeToInitialState("ababab", 2));

        System.out.println(minimumTimeToInitialState("abacaba", 3));

        System.out.println(minimumTimeToInitialState("abacaba", 4));

        System.out.println(minimumTimeToInitialState("abcbabcd", 2));

    }

    public static int minimumTimeToInitialState(String word, int k) {

        int N = word.length();

        int ans = 0;

        for (int i = k; i < N; i += k, ans++) {
            if (word.substring(0, N - i).equals(word.substring(i))) {
                return ans + 1;
            }
        }

        return N % k == 0 ? N / k : N / k + 1;
    }

}
