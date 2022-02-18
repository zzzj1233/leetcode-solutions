package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-01-21 11:28
 */
public class Leet2241 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            String s = LeetUtils.newString(100);
            if (violent(s) != lengthOfLongestSubstring(s)) {
                System.out.println("error");
                return;
            }
        }

    }

    public static int violent(String s) {
        char[] fre = new char[256];

        int max = 0;


        for (int i = 0, j = 0; j < s.length(); ) {
            char jChar = s.charAt(j);
            // 存在了
            if (fre[jChar] != 0) {
                while (i < j) {
                    if (s.charAt(i) == jChar) {
                        fre[s.charAt(i)] = 0;
                        i++;
                        break;
                    }
                    fre[s.charAt(i)] = 0;
                    i++;
                }
            }
            max = Math.max(max, j - i + 1);
            fre[s.charAt(j)] = 1;
            j++;
        }

        return max;
    }

    public static int lengthOfLongestSubstring(String s) {
        int[] table = new int[256];

        int l = 0;
        int r = 0;

        int ans = 0;

        while (r < s.length()) {
            char c = s.charAt(r);
            int index = c;
            // 缩短窗口
            if (table[index] > 0) {
                while (s.charAt(l) != c) {
                    table[s.charAt(l)]--;
                    l++;
                }
                table[s.charAt(l)]--;
                l++;
            }

            ans = Math.max(ans, r - l + 1);

            table[index]++;
            r++;
        }


        return ans;
    }

}
