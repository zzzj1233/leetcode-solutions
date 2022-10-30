package com.zzzj.leet;

public class Leet2063 {

    public long countVowels(String word) {
        long ans = 0;

        char[] chars = {'a', 'e', 'i', 'o', 'u'};

        int N = word.length();

        for (int i = 0; i < N; i++) {
            char c = word.charAt(i);

            for (char it : chars) {
                if (it == c) {
                    ans += (long) (i - 1) * (N - i);
                }
            }

        }

        return ans;
    }

}
