package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-04-25 14:08
 */
public class Leet58 {

    public static int lengthOfLastWord(String s) {
        int ans = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (ans == 0) {
                    continue;
                }
                return ans;
            }
            ans++;
        }

        return ans;
    }

}
