package com.zzzj.contest.week415;

import javax.swing.*;
import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-09-18 23:53
 */
public class Leet3291 {

    public static void main(String[] args) {

        System.out.println(minValidStrings(
                new String[]{"abc", "aaaaa", "bcdef"},
                "aabcdabc"
        ));

        System.out.println(minValidStrings(
                new String[]{"abababab", "ab"},
                "ababaababa"
        ));

    }

    public static int minValidStrings(String[] words, String target) {

        // 1. words[i]可以重复使用
        // 2. 求最少使用的words[i]的次数来形成target
        // 3. 无法形成target返回 -1
        // 4. words[i].length <= 5 * 10³
        // 5. sum(words[i].length) <= 10⁵
        // 6. target.length <= 5 * 10³

        // 考虑动态规划
        // f[i] = target[i]可以用的最小words[i]的次数
        // f[i] = f[j] != -1 && f[j] + minCount(target[j - i])

        // 如何快速求出
        // minCount(target[j - i]) ?
        // 考虑动态规划
        // 从后往前搜索 , j -> i , 使用trie, 如果搜索到了, 那么 f[i] = min(f[i], f[j] + 1)

        int N = target.length();

        int[] f = new int[N + 1];

        Arrays.fill(f, Integer.MAX_VALUE);

        f[0] = 0;

        Trie root = buildTrie(words);

        boolean[][] can = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            Trie node = root;
            for (int j = i; j < N; j++) {
                int index = target.charAt(j) - 'a';
                node = node.next[index];
                if (node == null)
                    break;
                else
                    can[i][j] = true;
            }
        }

        for (int i = 1; i <= N; i++) {

            for (int j = i; j <= N; j++) {

                // 搜索范围为, [ j - i ]
                if (f[i - 1] != Integer.MAX_VALUE && can[i - 1][j - 1])
                    f[j] = Math.min(
                            f[j],
                            f[i - 1] + 1
                    );

            }

        }

        // System.out.println("f = " + Arrays.toString(f));

        return f[N] == Integer.MAX_VALUE ? -1 : f[N];
    }

    public static Trie buildTrie(String[] words) {

        Trie root = new Trie();

        for (String word : words) {

            Trie node = root;

            for (int i = 0; i < word.length(); i++) {

                int index = word.charAt(i) - 'a';

                if (node.next[index] == null)
                    node.next[index] = new Trie();

                node = node.next[index];
            }

        }

        return root;
    }

    private static class Trie {
        Trie[] next = new Trie[26];
    }

}
