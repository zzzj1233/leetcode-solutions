package com.zzzj.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leet823 {

    public static void main(String[] args) {

        System.out.println(numFactoredBinaryTrees(new int[]{2, 4}));

        System.out.println(numFactoredBinaryTrees(new int[]{2, 4, 5, 10}));

    }

    public static int numFactoredBinaryTrees(int[] arr) {

        Arrays.sort(arr);

        int N = arr.length;

        Map<Integer, Integer> indexes = new HashMap<>(N);

        for (int i = 0; i < N; i++)
            indexes.put(arr[i], i);

        long[] f = new long[N];

        long ans = 0;

        final int MOD = 1000000007;

        for (int i = 0; i < N; i++) {

            int num = arr[i];

            f[i] = 1;

            for (int j = 0; j < i; j++) {

                if (num % arr[j] == 0) {
                    int other = num / arr[j];

                    if (indexes.containsKey(other)) {
                        f[i] = (f[i] + f[j] * f[indexes.get(other)]) % MOD;
                    }

                }

            }

            ans = (ans + f[i]) % MOD;
        }

        return (int) ans;
    }

}
