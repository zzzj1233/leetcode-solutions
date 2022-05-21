package com.zzzj.daily;

/**
 * @author Zzzj
 * @create 2022-05-09 09:49
 */
public class Leet942 {


    public static int[] diStringMatch(String s) {

        // 如果perm[i] < perm[i + 1]，那么s[i] == 'I'
        // 如果perm[i] > perm[i + 1]，那么 s[i] == 'D'


        int[] ans = new int[s.length() + 1];

        int max = s.length();
        int min = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                ans[i] = min++;
            } else {
                ans[i] = max--;
            }
        }

        if (s.charAt(s.length() - 1) == 'I') {
            ans[s.length()] = min;
        } else {
            ans[s.length()] = max;
        }

        return ans;
    }

}
