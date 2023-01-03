package com.zzzj.daily;

import com.zzzj.uf.Leet737;

/**
 * @author zzzj
 * @create 2023-01-03 11:07
 */
public class Leet2042 {

    public static boolean areNumbersAscending(String s) {

        int pre = -1;

        String[] words = s.split(" ");

        for (String word : words) {
            Integer num = parseInt(word);
            if (num == null) {
                continue;
            }
            if (num <= pre) {
                return false;
            }
            pre = num;
        }

        return true;
    }

    public static Integer parseInt(String word) {
        int num = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!Character.isDigit(c)) {
                return null;
            }
            num = num * 10 + Character.digit(c, 10);
        }
        return num;
    }

}
