package com.zzzj.str;

import java.util.List;

/**
 * @author zzzj
 * @create 2023-01-04 10:52
 */
public class Leet422 {

    public static boolean validWordSquare(List<String> words) {
        int N = words.size();

        for (int i = 0; i < N; i++) {
            String word = words.get(i);

            int M = word.length();

            if (N < M){
                return false;
            }

            for (int j = 0; j < M; j++) {
                String s = words.get(j);
                if (s.length() < (i + 1)) {
                    return false;
                }
                if (s.charAt(i) != word.charAt(j)) {
                    return false;
                }
            }

        }

        return true;
    }

}
