package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2023-01-04 10:34
 */
public class Leet408 {

    // "hi"
    // "02"
    public static void main(String[] args) {
        System.out.println(validWordAbbreviation("internationalization", "i12iz4n"));

        System.out.println(validWordAbbreviation("apple", "a2e"));
    }

    public static boolean validWordAbbreviation(String word, String abbr) {

        int N = word.length();

        int M = abbr.length();

        if (M > N) {
            return false;
        }

        int j = 0;

        int i = 0;

        while (i < M && j < N) {
            char c = abbr.charAt(i);

            if (Character.isDigit(c)) {
                int num = 0;
                while (i < M && Character.isDigit(abbr.charAt(i))) {
                    num = num * 10 + Character.digit(abbr.charAt(i), 10);
                    i++;
                    if (num == 0) {
                        return false;
                    }
                }
                // skip num次
                j += num;
            } else {
                if (word.charAt(j) != c) {
                    return false;
                }
                j++;
                i++;
            }

        }

        return j == N && i == M;
    }

}
