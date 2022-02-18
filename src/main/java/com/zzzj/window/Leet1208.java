package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-01-04 18:32
 */
public class Leet1208 {

    public static void main(String[] args) {
        int n = 1000;

        for (int i = 0; i < 10000; i++) {
            String s = LeetUtils.newString(n) + 1;
            String t = LeetUtils.newString(n) + 1;
            int cost = LeetUtils.random.nextInt(n) ;
            if (equalSubstring(s, t, cost) != violent(s, t, cost)) {
                System.out.println("ERROR");
                break;
            }
        }
    }

    private static int violent(String s, String t, int maxCost) {
        int ans = 0;

        for (int i = s.length() - 1; i >= 0; i--) {

            int cost = 0;

            for (int j = i; j >= 0; j--) {

                cost += Math.abs((int) s.charAt(j) - (int) t.charAt(j));

                if (cost > maxCost) {
                    break;
                }

                ans = Math.max(ans, i - j + 1);
            }

        }

        return ans;
    }

    public static int equalSubstring(String s, String t, int maxCost) {
        // 开始滑动
        int l = 0;
        int r = 0;

        int ans = 0;
        int cost = 0;

        while (r < s.length()) {
            cost += Math.abs((int) s.charAt(r) - (int) t.charAt(r));

            while (cost > maxCost && l <= r) {
                cost -= Math.abs((int) s.charAt(l) - (int) t.charAt(l));
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }

}
