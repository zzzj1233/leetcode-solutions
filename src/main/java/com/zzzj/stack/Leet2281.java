package com.zzzj.stack;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2024-06-19 17:36
 */
public class Leet2281 {

    public static void main(String[] args) {

        System.out.println(totalStrength(new int[]{1, 5, 4, 3, 6, 7, 8, 2}));

        // System.out.println(totalStrength(new int[]{1, 3, 1, 2}));

    }

    public static int totalStrength(int[] strength) {

        int N = strength.length;

        final int MOD = 1000000007;

        long[] s = new long[N + 1];

        long[] ss = new long[N + 2];

        for (int i = 1; i <= N; i++)
            s[i] = (s[i - 1] + strength[i - 1]) % MOD;

        for (int i = 1; i <= s.length; i++)
            ss[i] = (ss[i - 1] + s[i - 1]) % MOD;

        int[] left = new int[N];

        int[] right = new int[N];

        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < N; i++) {

            left[i] = i;

            Integer last = null;

            while (!stack.isEmpty() && strength[stack.peekLast()] >= strength[i]) {
                last = stack.removeLast();
                right[last] = i;
            }

            if (last != null)
                left[i] = left[last];

            stack.add(i);
        }

        while (!stack.isEmpty())
            right[stack.removeLast()] = N;

        long ans = 0;

        for (int i = 0; i < N; i++) {

            int l = left[i];

            int r = right[i] - 1;

            long tot = ((i - l + 1) * (ss[r + 2] - ss[i + 1]) - (r - i + 1) * (ss[i + 1] - ss[l])) % MOD;

            ans = (ans + strength[i] * tot) % MOD; // 累加贡献
        }

        return (int) (ans + MOD) % MOD;
    }

}
