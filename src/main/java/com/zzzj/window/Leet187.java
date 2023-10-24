package com.zzzj.window;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2021-12-22 18:55
 */
public class Leet187 {

    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    /*
        滚筒hash举例

        1. 123的hash
        (((1 * 26) + 2) * 26) + 3 = 731

        2. 123 -> 234 : 去掉1再加上4
        (((2 * 26) + 3) * 26) + 4 = 1434

        ( 26 * 26 = 676 , 731 - 676 = 55 )

        55 = (2 * 26) + 3 , 那么 55 * 26 + 4 = ((2 * 26) + 3) * 26 + 4 = 1434
     */
    public static List<String> findRepeatedDnaSequences(String s) {

        Map<Long, Integer> hashIndex = new HashMap<>();

        Set<Integer> repeat = new HashSet<>();

        int M = 10;

        int N = s.length();

        if (N < M) return Collections.emptyList();

        final int MOD = 1000000007;

        long h = 0;

        int prime = 256;

        for (int i = 0; i < M; i++) {
            h = (h * prime) % MOD + index(s.charAt(i));
        }

        long H = 1;

        for (int i = 0; i < 9; i++)
            H = (H * prime) % MOD;

        hashIndex.put(h, 0);

        // 1234
        // 1 * 1000
        for (int i = M; i < N; i++) {
            // 减去第一个
            int prev = index(s.charAt(i - 10));

            h -= (H * prev) % MOD;
            if (h < 0)
                h += MOD;
            h %= MOD;

            // 加上当前
            h = (h * prime) % MOD + index(s.charAt(i));

            if (hashIndex.containsKey(h))
                repeat.add(hashIndex.get(h));
            else
                hashIndex.put(h, i - 9);
        }

        return repeat.stream().map(index -> s.substring(index, index + 10)).collect(Collectors.toList());
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

}
