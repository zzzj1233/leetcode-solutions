package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-08-26 16:04
 */
public class Leet1255 {

    public static void main(String[] args) {
        System.out.println(maxScoreWords(new String[]{"add", "dda", "bb", "ba", "add"}, LeetUtils.convertChars1("[\"a\",\"a\",\"a\",\"a\",\"b\",\"b\",\"b\",\"b\",\"c\",\"c\",\"c\",\"c\",\"c\",\"d\",\"d\",\"d\"]"), LeetUtils.convertInts1("[3,9,8,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]")));
    }

    public static int maxScoreWords(String[] words, char[] letters, int[] score) {

        int[] counts = new int[26];

        for (char letter : letters) {
            counts[letter - 'a']++;
        }

        int[][] wordCounts = new int[words.length][26];

        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            for (int j = 0; j < word.length(); j++) {

                wordCounts[i][word.charAt(j) - 'a']++;

            }

        }

        return dfs(wordCounts, counts, score, 0);
    }

    public static int dfs(int[][] wordCounts, int[] counts, int[] score, int used) {

        int result = 0;

        OUTER:
        for (int i = 0; i < wordCounts.length; i++) {
            if (((used >> i) & 1) == 1) {
                continue;
            }

            int[] wordCount = wordCounts[i];

            for (int j = 0; j < wordCount.length; j++) {
                if (counts[j] < wordCount[j]) {
                    continue OUTER;
                }
            }

            int totalScore = 0;

            for (int j = 0; j < wordCount.length; j++) {
                counts[j] -= wordCount[j];

                totalScore += score[j] * wordCount[j];
            }

            result = Math.max(result, totalScore + dfs(wordCounts, counts, score, used | (1 << i)));

            for (int j = 0; j < wordCount.length; j++) {
                counts[j] += wordCount[j];
            }
        }

        return result;
    }
}
