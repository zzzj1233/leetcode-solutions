package com.zzzj.contest.week336;

/**
 * @author zzzj
 * @create 2023-08-02 16:42
 */
public class Leet2586 {

    public static void main(String[] args) {

        System.out.println(vowelStrings(new String[]{"are", "amy", "u"}, 0, 2));

        System.out.println(vowelStrings(new String[]{"hey", "aeo", "mu", "ooo", "artro"}, 1, 4));

    }

    public static int vowelStrings(String[] words, int left, int right) {

        right = Math.min(right, words.length - 1);

        int ans = 0;

        char[] chars = {'a', 'e', 'i', 'o', 'u'};

        for (int i = left; i <= right; i++) {
            String word = words[i];
            if (word.isEmpty()) continue;

            boolean ok = false;

            for (char it : chars) {

                if (word.charAt(0) == it) {
                    ok = true;
                }

            }

            if (!ok) continue;

            ok = false;

            for (char it : chars) {

                if (word.charAt(word.length() - 1) == it) {
                    ok = true;
                }

            }

            if (ok) ans++;
        }

        return ans;
    }

}
