package com.zzzj.str;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-10-24 11:53
 */
public class Leet1316 {

    public static void main(String[] args) {

        System.out.println(distinctEchoSubstrings("abcabcabc"));
        System.out.println(distinctEchoSubstrings("leetcodeleetcode"));

    }

    public static int distinctEchoSubstrings(String text) {

        int N = text.length();

        int PRIME = 109;

        final int MOD = 1000000007;

        Set<String> repeated = new HashSet<>();

        for (int i = 0; i < N - 1; i++) {

            long hash1 = text.charAt(i);

            long hash2 = text.charAt(i + 1);

            long base = 1;

            if (hash1 == hash2) {
                repeated.add(String.valueOf(text.charAt(i)));
            }

            for (int x = i + 1, y = x + 1; y + 1 < N; x++) {

                char c1 = text.charAt(x);

                hash1 = (hash1 * PRIME) % MOD + c1;

                hash2 -= (base * c1) % MOD;

                base = (base * PRIME) % MOD;

                if (hash2 < 0)
                    hash2 += MOD;

                hash2 = (hash2 * PRIME) % MOD + text.charAt(y++);
                hash2 = (hash2 * PRIME) % MOD + text.charAt(y++);

                if (hash1 == hash2) {
                    repeated.add(text.substring(i, y));
                }

            }
        }

        return repeated.size();
    }

}
