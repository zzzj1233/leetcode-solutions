package com.zzzj.leet;

public class Leet1347 {

    public static int minSteps(String s, String t) {

        int[] bucket = new int[26];

        int N = s.length();

        for (int i = 0; i < N; i++) {
            bucket[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < N; i++) {
            bucket[t.charAt(i) - 'a']++;
        }

        int ans = 0;

        for (int i = 0; i < 26; i++) {
            if (bucket[i] > 0) {
                ans += bucket[i];
            }
        }

        return ans;
    }

}
