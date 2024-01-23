package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-01-03 17:03
 */
public class LCP49 {

    public static void main(String[] args) {

        System.out.println(ringGame(new long[]{19, 18, 1, 19, 19}));

        System.out.println(ringGame(new long[]{1, 32255, 15848}));

        System.out.println(ringGame(new long[]{5, 4, 6, 2, 7}));

        System.out.println(ringGame(new long[]{12, 7, 11, 3, 9}));

    }

    public static long ringGame(long[] challenge) {

        int N = challenge.length;

        long ans = Arrays.stream(challenge).max().orElse(0);

        long max = 0;

        for (long v : challenge) max |= v;

        for (int i = 63; i >= 0; i--)
            if ((ans & (1L << i)) != 0)
                if (check(challenge, ans ^ (1L << i), max))
                    ans ^= (1L << i);

        return ans;
    }

    public static boolean check(long[] challenge, long expect, long max) {

        int N = challenge.length;

        int start = 0;

        while (start < N) {

            while (start < N) {
                if (challenge[start] <= expect)
                    break;
                start++;
            }

            if (start >= N)
                break;

            long v = expect | challenge[start];

            int left = start - 1;

            for (; left >= 0 && challenge[left] <= v; left--) {
                v |= challenge[left];
            }

            int right = start + 1;

            for (; right < N && challenge[right] <= v; right++) {
                v |= challenge[right];
            }

            if (left < 0) {

                if (right < N) {

                    int R = N - 1;

                    while (R >= right && challenge[R] <= v) {
                        v |= challenge[R];
                        R--;
                    }
                }

            }

            if (right >= N) {
                if (left >= 0) {
                    int L = 0;

                    while (L <= left && challenge[L] <= v) {
                        v |= challenge[L];
                        L++;
                    }
                }
            }

            if (v == max)
                return true;

            start++;
        }

        return false;
    }


}
