package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-01-28 15:53
 */
public class Leet1324 {

    public static void main(String[] args) {
        System.out.println(printVertically("TO BE OR NOT TO BE"));
    }

    public static List<String> printVertically(String s) {

        String[] words = s.split(" ");

        int N = words.length;

        List<String> ans = new ArrayList<>(N);

        int M = Arrays.stream(words).mapToInt(String::length).max().getAsInt();

        for (int i = 0; i < M; i++) {
            StringBuilder builder = new StringBuilder(N);
            for (int j = 0; j < N; j++) {
                builder.append(i >= words[j].length() ? " " : words[j].charAt(i));
            }
            int k = N - 1;
            for (; k >= 0; k--) {
                if (builder.charAt(k) != ' ') {
                    break;
                }
            }
            builder.setLength(k + 1);
            ans.add(builder.toString());
        }

        return ans;
    }


}
