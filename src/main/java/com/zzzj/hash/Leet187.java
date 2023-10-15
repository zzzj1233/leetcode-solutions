package com.zzzj.hash;

import java.util.*;
import java.util.stream.Collectors;

public class Leet187 {

    public static void main(String[] args) {

        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));

    }

    public static int index(char x) {
        switch (x) {
            case 'A':
                return 1;
            case 'C':
                return 2;
            case 'G':
                return 3;
            case 'T':
            default:
                return 4;
        }
    }

    public static List<String> findRepeatedDnaSequences(String s) {

        Map<Long, Integer> hashIndex = new HashMap<>();

        Set<Integer> repeat = new HashSet<>();

        int LEN = 10;

        int N = s.length();

        if (N < LEN) return Collections.emptyList();

        final int MOD = 1000000007;

        long h = 0;

        int R = 256;

        for (int i = 0; i < LEN; i++) {
            h = (h * R) % MOD + index(s.charAt(i));
        }

        long H = 1;

        for (int i = 0; i < 9; i++) {
            H *= R;
            H %= MOD;
        }

        hashIndex.put(h, 0);

        // 1234
        // 1 * 1000
        for (int i = LEN; i < N; i++) {
            // 减去第一个
            int prev = index(s.charAt(i - 10));
            h -= (H * prev) % MOD;
            if (h < 0)
                h += MOD;
            h %= MOD;

            // 加上当前
            h = (h * R) % MOD + index(s.charAt(i));

            if (hashIndex.containsKey(h))
                repeat.add(hashIndex.get(h));
            else
                hashIndex.put(h, i - 9);
        }

        return repeat.stream().map(index -> s.substring(index, index + 10)).collect(Collectors.toList());
    }
}
