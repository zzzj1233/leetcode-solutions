package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-04-23 17:32
 */
public class Leet2606 {

    public static void main(String[] args) {
        System.out.println(maximumCostSubstring("adaa", "d", new int[]{-1000}));
    }

    public static int maximumCostSubstring(String s, String chars, int[] vals) {

        int[] cost = new int[26];

        for (int i = 0; i < 26; i++) {
            cost[i] = i + 1;
        }

        for (int i = 0; i < vals.length; i++) {
            char c = chars.charAt(i);
            int val = vals[i];
            cost[c - 'a'] = val;
        }

        // ====================================

        int N = s.length();

        int maxCost = 0;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int c = cost[s.charAt(i) - 'a'];
            maxCost = Math.max(0, maxCost + c);
            ans = Math.max(ans, maxCost);
        }

        return ans;
    }

}
