package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-08-30 19:20
 */
public class Leet1048 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int N = LeetUtils.random.nextInt(1000) + 1;
            String[] arr = new String[N];

            for (int j = 0; j < N; j++) {
                int wordLen = LeetUtils.random.nextInt(16) + 1;
                arr[j] = LeetUtils.randomString(wordLen, false);
            }

            String[] origin = Arrays.copyOfRange(arr, 0, arr.length);

            String[] origin2 = Arrays.copyOfRange(arr, 0, arr.length);

            if (longestStrChain(arr) != right(origin)) {
                System.out.println("Error");
                System.out.println(LeetUtils.stringsToLeetCode(origin2));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));

        int N = words.length;

        if (N == 0) {
            return 0;
        }

        int[] dp = new int[N];


        int ans = 1;

        int minLen = words[0].length();

        int i = 0;

        while (i < N && words[i].length() == minLen) {
            dp[i] = 1;
            i++;
        }

        int left = 0;
        int next = 0;

        for (; i < N; i++) {

            if (words[i].length() > words[i - 1].length()) {
                left = next;
                next = i;
            }

            dp[i] = 1;

            for (int j = left; j < next; j++) {
                if (isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            ans = Math.max(dp[i], ans);
        }

        return ans;
    }

    /**
     * 判断a是否是b的前身 是返回true 如 "bda" 是"bdca"的前身
     *
     * @param a
     * @param b
     * @return
     */
    private static boolean isPredecessor(String a, String b) {
        int i = 0, j = 0;
        int m = a.length(), n = b.length();
        if ((m + 1) != n) return false;
        while (i < m && j < n) {
            if (a.charAt(i) == b.charAt(j)) i++;
            j++;
        }
        return i == m;
    }


    public static int right(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int n = words.length;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            String a = words[i];
            for (int j = i + 1; j < n; j++) {
                String b = words[j];
                if (isPredecessor(a, b)) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                    res = Math.max(dp[j], res);
                }
            }
        }
        return res + 1;
    }


}
