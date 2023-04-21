package com.zzzj.dpoint;

/**
 * @author zzzj
 * @create 2023-04-13 15:03
 */
public class Leet1754 {

    // abcabc
    // abdcaba

    // abcabcabdcab
    public static void main(String[] args) {
        System.out.println(largestMerge("cabaa", "bcaaa"));

        System.out.println(largestMerge("abcabc", "abdcaba"));
    }

    public static String largestMerge(String word1, String word2) {

        int index1 = 0;

        int index2 = 0;

        int N = word1.length();

        int M = word2.length();

        StringBuilder builder = new StringBuilder(N + M);

        while (index1 < N || index2 < M) {

            if (index1 == N) {
                builder.append(word2.charAt(index2));
                index2++;
            } else if (index2 == M) {
                builder.append(word1.charAt(index1));
                index1++;
            } else if (word1.substring(index1).compareTo(word2.substring(index2)) >= 0) {
                builder.append(word1.charAt(index1));
                index1++;
            } else {
                builder.append(word2.charAt(index2));
                index2++;
            }

        }

        return builder.toString();
    }

}
