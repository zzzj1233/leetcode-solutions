package com.zzzj.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2024-01-19 11:48
 */
public class Leet2227 {

    private static class Encrypter {

        private final Map<Character, String> kv;

        private final Map<String, Set<Character>> vk;

        private final Map<String, Integer> cnt;

        public Encrypter(char[] keys, String[] values, String[] dictionary) {

            kv = new HashMap<>(64);

            vk = new HashMap<>(64);

            cnt = new HashMap<>(dictionary.length);

            int N = keys.length;

            for (int i = 0; i < N; i++) {
                kv.put(keys[i], values[i]);
                vk.computeIfAbsent(values[i], s -> new HashSet<>())
                        .add(keys[i]);
            }

            for (String s : dictionary) {
                String encrypt = encrypt(s);
                cnt.put(encrypt, cnt.getOrDefault(encrypt, 0) + 1);
            }

        }

        public String encrypt(String word1) {

            int N = word1.length();

            StringBuilder builder = new StringBuilder(N << 1);

            for (int i = 0; i < N; i++) {
                String v = kv.get(word1.charAt(i));
                if (v == null)
                    return "";
                builder.append(v);
            }

            return builder.toString();
        }

        public int decrypt(String word2) {
            return cnt.getOrDefault(word2, 0);
        }

    }

}
