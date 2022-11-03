package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2022-11-02 18:02
 */
public class Leet1400 {

    public static boolean canConstruct(String s, int k) {
        int N = s.length();

        if (N < k) {
            return false;
        }

        if (N == k) {
            return true;
        }

        int[] bucket = new int[26];

        for (int i = 0; i < N; i++) {
            bucket[s.charAt(i) - 'a']++;
        }

        int odd = 0;

        for (int i = 0; i < 26; i++) {
            if (bucket[i] % 2 != 0) {
                odd++;
            }
        }

        return odd <= k;
    }

}
