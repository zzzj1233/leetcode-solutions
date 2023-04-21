package com.zzzj.str;

/**
 * @author zzzj
 * @create 2023-04-12 18:25
 */
public class Leet2390 {

    public static String removeStars(String s) {

        int N = s.length();

        StringBuilder builder = new StringBuilder(N);

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '*') {
                builder.setLength(builder.length() - 1);
            } else {
                builder.append(s.charAt(i));
            }
        }

        return builder.toString();
    }

}
