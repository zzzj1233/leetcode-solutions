package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-06-22 11:14
 */
public class Leet2425 {

    public static char firstUniqChar(String s) {
        int[] bucket = new int[26];

        char ans = ' ';

        for (int i = 0; i < s.length(); i++) {
            bucket[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (bucket[s.charAt(i) - 'a'] == 1) {
                ans = s.charAt(i);
                return ans;
            }
        }

        return ans;
    }

}
