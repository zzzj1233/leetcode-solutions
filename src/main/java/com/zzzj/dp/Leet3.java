package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-10 22:51
 */
public class Leet3 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {

        if (s == null || s.isEmpty()){
            return 0;
        }

        int N = s.length();

        int[] dp = new int[N];

        int[] dict = new int[256];

        Arrays.fill(dict, -1);

        dp[0] = 1;
        dict[s.charAt(0)] = 0;

        int ans = 0;

        for (int i = 1; i < N; i++) {
            // 当前字母上次出现的位置
            int preIndex = dict[s.charAt(i)];

            dp[i] = Math.min(dp[i - 1] + 1, i - preIndex);

            ans = Math.max(ans, dp[i]);

            dict[s.charAt(i)] = i;
        }

        return ans;
    }


}
