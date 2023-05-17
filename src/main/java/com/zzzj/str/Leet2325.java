package com.zzzj.str;

/**
 * @author zzzj
 * @create 2023-05-12 15:49
 */
public class Leet2325 {

    public static void main(String[] args) {
        System.out.println(decodeMessage(
                "the quick brown fox jumps over the lazy dog",
                "vkbs bs t suepuv"
        ));
    }

    public static String decodeMessage(String key, String message) {

        char lastC = 'a';

        int[] mapping = new int[26];

        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (c == ' ') {
                continue;
            }

            int idx = c - 'a';

            if (mapping[idx] == 0) {
                mapping[idx] = lastC;
                lastC++;
            }

        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);

            if (c == ' ') {
                builder.append(c);
                continue;
            }

            int idx = c - 'a';

            if (mapping[idx] == 0) {
                continue;
            }
            builder.append((char) mapping[idx]);
        }

        return builder.toString();
    }

}
