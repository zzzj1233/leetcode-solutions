package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-27 18:42
 */
public class Leet1657 {

    public static void main(String[] args) {
        System.out.println(closeStrings("uau", "ssx"));
    }

    // "uau"
    // "ssx"
    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] bucket1 = new int[26];
        int[] bucket2 = new int[26];

        for (int i = 0; i < word1.length(); i++) {
            bucket1[word1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < word1.length(); i++) {
            bucket2[word2.charAt(i) - 'a']++;
        }

        Arrays.sort(bucket1);
        Arrays.sort(bucket2);

        for (int i = 0; i < 26; i++) {
            if (bucket1[i] != bucket2[i]) {
                return false;
            }
        }


        return true;
    }

}
