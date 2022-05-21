package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-11 14:20
 */
public class Leet318 {

    public static void main(String[] args) {
        maxProduct(new String[]{"abcz"});
    }

    public static int maxProduct(String[] words) {
        int N = words.length;

        int[] mask = new int[N];

        for (int i = 0; i < N; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                int index = word.charAt(j) - 'a';
                mask[i] |= 1 << index;
            }
        }

        int ans = 0;

        for (int i = 0; i < words.length; i++) {

            for (int j = i + 1; j < words.length; j++) {
                final int m1 = mask[i];
                final int m2 = mask[j];
                if ((m1 & m2) > 0) {
                    continue;
                }
                ans = Math.max(ans, words[i].length() * words[j].length());
            }

        }

        return ans;
    }

}
