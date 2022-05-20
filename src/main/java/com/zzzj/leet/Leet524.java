package com.zzzj.leet;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-05-20 12:16
 */
public class Leet524 {

    public static void main(String[] args) {
        // ["apple","ewaf","awefawfwaf","awef","awefe","ewafeffewafewf"]
        System.out.println(findLongestWord("aewfafwafjlwajflwajflwafj", Arrays.asList(
                "apple",
                "ewaf",
                "awefawfwaf",
                "awef",
                "awefe",
                "ewafeffewafewf"
        )));
    }

    // "pbacplea"
    // ["ale","apple","monkey","plea"]
    public static String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o2.length() - o1.length();
        });


        final char[] chars = s.toCharArray();

        for (String s1 : dictionary) {
            boolean match = true;
            if (s1.length() > chars.length) {
                continue;
            }

            // chars可删
            int j = 0;

            for (int i = 0; i < chars.length && j < s1.length(); ) {
                if (chars[i] == s1.charAt(j)) {
                    j++;
                }
                i++;
            }

            if (j == s1.length()) {
                return s1;
            }
        }

        return "";
    }

}
