package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-08-18 16:56
 */
public class Leet2606 {

    public static void main(String[] args) {

        System.out.println(maximumCostSubstring("adaa", "d", new int[]{-1000}));

    }

    public static int maximumCostSubstring(String s, String chars, int[] vals) {

        int[] cost = new int[27];

        for (int i = 0; i < cost.length; i++) cost[i] = i + 1;

        for (int i = 0; i < chars.length(); i++) cost[chars.charAt(i) - 'a'] = vals[i];

        int maxCost = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            maxCost = Math.max(
                    0,
                    maxCost + cost[s.charAt(i) - 'a']
            );

            ans = Math.max(ans, maxCost);
        }

        return ans;
    }

}
