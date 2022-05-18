package com.zzzj.daily;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-17 11:47
 */
public class Leet953 {

    public static void main(String[] args) {
        System.out.println(isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
    }

    // hello
    // leetcode
    // hlabcdefgijkmnopqrstuvwxyz
    public static boolean isAlienSorted(String[] words, String order) {
        boolean[][] dict = new boolean[26][26];

        int index = order.charAt(0) - 'a';

        for (int j = 1; j < order.length(); j++) {
            dict[index][order.charAt(j) - 'a'] = true;
        }

        for (int j = 1; j < order.length(); j++) {
            index = order.charAt(j) - 'a';
            dict[index] = Arrays.copyOfRange(dict[order.charAt(j - 1) - 'a'], 0, 26);
            dict[index][order.charAt(j - 1) - 'a'] = false;
        }

        OUTER:
        for (int i = 1; i < words.length; i++) {
            String cur = words[i];
            String pre = words[i - 1];

            int len = Math.min(cur.length(), pre.length());

            for (int j = 0; j < len; j++) {
                if (pre.charAt(j) == cur.charAt(j)) {
                    continue;
                }
                if (!dict[pre.charAt(j) - 'a'][cur.charAt(j) - 'a']) {
                    return false;
                }
                continue OUTER;
            }
            if (pre.length() > cur.length()) {
                return false;
            }
        }


        return true;
    }

}
