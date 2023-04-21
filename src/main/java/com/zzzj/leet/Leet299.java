package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-04-13 14:44
 */
public class Leet299 {

    public static void main(String[] args) {

//        System.out.println(getHint("1807", "7810"));
//
//        System.out.println(getHint("1123", "0111"));

        System.out.println(getHint("11", "10"));

    }

    // "11"
    // "10"
    public static String getHint(String secret, String guess) {

        int A = 0;

        int B = 0;

        int N = secret.length();

        Map<Character, Integer> cnt = new HashMap<>(N);

        for (int i = 0; i < N; i++) {
            char c = secret.charAt(i);

            if (c == guess.charAt(i)) {
                A++;
            } else {
                cnt.put(c, cnt.getOrDefault(c, 0) + 1);
            }

        }

        for (int i = 0; i < N; i++) {

            char c = guess.charAt(i);

            if (c == secret.charAt(i)) {
                continue;
            } else if (cnt.getOrDefault(c, 0) > 0) {
                B++;
                cnt.put(c, cnt.get(c) - 1);
            }

        }

        return String.format("%dA%dB", A, B);
    }

}
