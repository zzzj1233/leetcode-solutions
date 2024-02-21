package com.zzzj.contest.week383;

/**
 * @author zzzj
 * @create 2024-02-04 10:27
 */
public class Q4 {

    public static void main(String[] args) {

        // 1
        System.out.println(minimumTimeToInitialState("blyfeblyfeblyfeblyfe", 5));

        // 3
        System.out.println(minimumTimeToInitialState("abaabaa", 2));

        System.out.println(minimumTimeToInitialState("bbb", 2));

        // 2
        System.out.println(minimumTimeToInitialState("abacaba", 3));

        // 2
        System.out.println(minimumTimeToInitialState("baba", 3));

        System.out.println(minimumTimeToInitialState("abacaba", 4));

        System.out.println(minimumTimeToInitialState("abcbabcd", 2));

    }

    public static int minimumTimeToInitialState(String word, int k) {

        int N = word.length();

        final int MOD = 1000000007;

        int base1 = 1;

        long num1 = 0;

        int index1 = 0;

        long base2 = 1;

        long num2 = 0;

        int index2 = N - 1;

        int ans = N % k == 0 ? N / k : N / k + 1;

        while (N - (N - index2) >= k) {

            int c1 = word.charAt(index1) - 'a' + 1;
            num1 = ((num1 * 26) % MOD + c1) % MOD;

            int c2 = word.charAt(index2) - 'a' + 1;
            num2 = (num2 + (c2 * base2) % MOD) % MOD;
            base2 = (base2 * 26) % MOD;

            int len = N - (N - index2);

            if (num1 == num2 && len % k == 0)
                ans = len / k;

            index1++;
            index2--;
        }

        return ans;
    }


}
