package com.zzzj.daily;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-04-19 21:10
 */
public class Leet821 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.randomString(LeetUtils.random.nextInt(2000) + 1, false);
            char c = str.charAt(LeetUtils.random.nextInt(str.length()));
            if (!Arrays.equals(right(str, c), shortestToChar(str, c))) {
                System.out.println(str);
                System.out.println(c);
                return;
            }
        }
    }

    public static int[] right(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];

        for (int i = 0, idx = -n; i < n; ++i) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = i - idx;
        }

        for (int i = n - 1, idx = 2 * n; i >= 0; --i) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = Math.min(ans[i], idx - i);
        }
        return ans;
    }

    public static int[] shortestToChar(String s, char c) {

        int index1 = -1;

        int[] ans = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                if (index1 == -1) {
                    index1 = i;
                    for (int j = 0; j <= index1; j++) {
                        ans[j] = index1 - j;
                    }
                } else {
                    int mid = (i + index1) / 2;
                    for (int j = index1 + 1; j <= mid; j++) {
                        ans[j] = j - index1;
                    }
                    for (int j = mid + 1; j <= i; j++) {
                        ans[j] = i - j;
                    }
                    index1 = i;
                }
            }
        }

        if (index1 != s.length() - 1) {
            for (int i = index1 + 1; i < s.length(); i++) {
                ans[i] = i - index1;
            }
        }

        return ans;
    }


}
