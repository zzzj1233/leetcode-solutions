package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-05-29 13:01
 */
public class Leet921 {

    public int minAddToMakeValid(String s) {

        int count = 0;

        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                ans++;
                count = 0;
            }
        }

        ans += count;

        return ans;
    }

}
