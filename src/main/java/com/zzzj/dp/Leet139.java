package com.zzzj.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-04-12 16:30
 */
public class Leet139 {

    public static void main(String[] args) {

        System.out.println(wordBreak("aaaaaaa", Arrays.asList("aaaa", "aaa")));

        System.out.println(wordBreak("applepie", Arrays.asList("pie", "pear", "apple", "peach")));

        System.out.println(wordBreak("bb", Arrays.asList("a", "b", "bbb", "bbbb")));

        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));

        System.out.println(wordBreak("applepenapple", Arrays.asList("apple", "pen")));

        System.out.println(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));

    }

    public static boolean wordBreak(String s, List<String> wordDict) {

        int N = s.length();

        boolean[] f = new boolean[N + 1];

        f[0] = true;

        for (int i = 1; i <= N; i++) {

            OUTER:
            for (String w : wordDict) {

                int M = w.length();

                if (i - M < 0)
                    continue;

                for (int j = M - 1, k = 1; j >= 0; j--, k++) {
                    if (w.charAt(j) != s.charAt(i - k))
                        continue OUTER;
                }

                f[i] |= f[i - M];
            }

        }

        return f[N];
    }

}
