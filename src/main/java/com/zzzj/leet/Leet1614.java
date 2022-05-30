package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-30 18:43
 */
public class Leet1614 {


    public static int maxDepth(String s) {
        int leftCount = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else if (s.charAt(i) == ')') {
                leftCount--;
            }
            ans = Math.max(ans, leftCount);
        }

        return ans;
    }

}
