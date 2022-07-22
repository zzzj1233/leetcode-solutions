package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-11 14:20
 */
public class Leet318 {

    public static void main(String[] args) {
        System.out.println(maxProduct(new String[]{"abcw", "baz", "foo", "bar", "fxyz", "abcdef"}));
        System.out.println(maxProduct(new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}));
        System.out.println(maxProduct(new String[]{"a", "aa", "aaa", "aaaa"}));
    }

    public static int maxProduct(String[] words) {
        int N = words.length;

        int[] mask = new int[N];

        for (int i = 0; i < N; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                mask[i] |= 1 << (c - 'a');
            }
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {

            long bit1 = mask[i];

            for (int j = i + 1; j < N; j++) {

                if (i == j) {
                    continue;
                }

                long bit2 = mask[j];

                if ((bit1 & bit2) > 0) {
                    continue;
                }

                ans = Math.max(ans, words[i].length() * words[j].length());
            }

        }

        return ans;
    }

}
