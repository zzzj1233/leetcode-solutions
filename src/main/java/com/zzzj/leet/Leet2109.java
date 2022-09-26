package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-09-26 17:16
 */
public class Leet2109 {

    public static String addSpaces(String s, int[] spaces) {
        int N = s.length();

        StringBuilder builder = new StringBuilder(N + spaces.length);

        int index = 0;

        for (int i = 0; i < N; i++) {
            if (index < spaces.length && i == spaces[index]) {
                builder.append(' ');
                index++;
            }
            builder.append(s.charAt(i));
        }

        return builder.toString();
    }

}
