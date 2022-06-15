package com.zzzj.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-06-12 22:06
 */
public class Leet890 {

    public static void main(String[] args) {
        System.out.println(findAndReplacePattern(new String[]{"mee", "aqq", "dkd", "ccc"}, "abb"));
    }

    public static List<String> findAndReplacePattern(String[] words, String pattern) {

        char[] index = new char[26];
        char[] index2 = new char[26];

        List<String> ans = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            Arrays.fill(index, ' ');
            Arrays.fill(index2, ' ');

            String word = words[i];

            int j = 0;

            for (j = 0; j < word.length(); j++) {
                char p = pattern.charAt(j);

                char w = word.charAt(j);

                char c = index[p - 'a'];

                char c2 = index2[w - 'a'];

                // w = c
                // p = a
                // c = index[p]
                // c2 = index2[w]

                if (c2 == ' ' && c == ' ') {
                    index[p - 'a'] = w;
                    index2[w - 'a'] = p;
                } else if (w != c || p != c2) {
                    break;
                }
            }

            if (j == word.length()) {
                ans.add(word);
            }

        }

        return ans;
    }

}
