package com.zzzj.contest.week384;

import java.util.Arrays;
import java.util.Comparator;

public class Leet3035 {

    public static void main(String[] args) {

        System.out.println(maxPalindromesAfterOperations(new String[]{"abc", "ab"}));

    }

    public static int maxPalindromesAfterOperations(String[] words) {

        int ans = 0;

        int[] tab = new int[26];

        for (String word : words)
            for (int i = 0; i < word.length(); i++)
                tab[word.charAt(i) - 'a']++;

        int tot = 0;

        int odd = 0;

        for (int i = 0; i < 26; i++)
            if ((tab[i] & 1) == 1) {
                tot += tab[i] - 1;
                odd++;
            } else
                tot += tab[i];

        Arrays.sort(words, Comparator.comparingInt(String::length));

        for (String word : words) {

            int len = word.length();

            if (len % 2 == 0) {
                tot -= len;
            } else if (odd > 0) {
                tot -= len - 1;
                odd -= 1;
            }

            if (tot < 0)
                break;

            ans++;
        }

        return ans;
    }


}
