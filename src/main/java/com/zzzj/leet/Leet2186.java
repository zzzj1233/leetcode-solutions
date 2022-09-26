package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-09-22 10:52
 */
public class Leet2186 {

    public static int minSteps(String s, String t) {
        int[] c1 = new int[26];

        for (int i = 0; i < s.length(); i++) {
            c1[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            c1[t.charAt(i) - 'a']--;
        }

        int ans = 0;

        for (int i = 0; i < 26; i++) {
            ans += Math.abs(c1[i]);
        }

        return ans;
    }

}
