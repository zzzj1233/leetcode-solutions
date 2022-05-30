package com.zzzj.daily;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-26 10:58
 */
public class Leet467 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.randomString(50, false);

            if (findSubstringInWraproundString(str) != right(str)) {
                System.out.println(str);
                System.out.println(findSubstringInWraproundString(str));
                System.out.println(right(str));
                return;
            }
        }
    }


    public static int findSubstringInWraproundString(String p) {
        char[] chars = p.toCharArray();

        int[] dp = new int[chars.length];
        int[] count = new int[26];

        dp[0] = 1;
        count[chars[0] - 'a'] = 1;

        for (int i = 1; i < chars.length; i++) {
            char per = chars[i - 1];
            char cur = chars[i];

            if (isNext(per, cur)) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }

            count[cur - 'a'] = Math.max(count[cur - 'a'], dp[i]);
        }

        return Arrays.stream(count).sum();
    }

    public static boolean isNext(char per, char cur) {
        if (per == 'z') {
            return cur == 'a';
        }
        return per + 1 == cur;
    }

    public static int right(String _p) {
        char[] cs = _p.toCharArray();
        int n = cs.length, ans = 0;
        int[] max = new int[26];
        max[cs[0] - 'a']++;
        for (int i = 1, j = 1; i < n; i++) {
            int c = cs[i] - 'a', p = cs[i - 1] - 'a';
            if ((p == 25 && c == 0) || p + 1 == c) j++;
            else j = 1;
            max[c] = Math.max(max[c], j);
        }
        for (int i = 0; i < 26; i++) ans += max[i];
        return ans;
    }


}
