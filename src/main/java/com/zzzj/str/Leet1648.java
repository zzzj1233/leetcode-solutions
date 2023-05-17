package com.zzzj.str;

/**
 * @author zzzj
 * @create 2023-05-12 16:08
 */
public class Leet1648 {

    public static int countConsistentStrings(String allowed, String[] words) {

        boolean[] allow = new boolean[32];

        for (int i = 0; i < allowed.length(); i++) {
            allow[allowed.charAt(i) - 'a'] = true;
        }

        int ans = 0;

        OUT:
        for (String word : words) {

            for (int i = 0; i < word.length(); i++) {
                if (!allow[word.charAt(i) - 'a']){
                    continue OUT;
                }
            }

            ans++;
        }

        return ans;
    }

}
