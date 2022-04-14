package com.zzzj.hot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-04-14 16:33
 */
public class Leet131 {

    public static void main(String[] args) {
        System.out.println(partition("abbab"));
        System.out.println("~");
    }

    static boolean[][] dp;

    public static List<List<String>> partition(String s) {
        char[] chars = s.toCharArray();

        dp = dp(chars);

        List<List<String>> ans = new ArrayList<>();

        dfs(chars, 0, new LinkedList<>(), ans);

        return ans;
    }

    public static boolean[][] dp(char[] chars) {
        boolean[][] dp = new boolean[chars.length][chars.length];

        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = true;
        }

        // 斜角线
        for (int i = 1; i < chars.length; i++) {
            dp[i - 1][i] = chars[i - 1] == chars[i];
        }

        // 填空
        int i = chars.length - 3;
        int j = chars.length - 1;

        while (i >= 0) {
            int k = chars.length - 1;
            while (k >= j) {
                dp[i][k] = chars[i] == chars[k] && dp[i + 1][k - 1];
                k--;
            }
            i--;
            j--;
        }

        return dp;
    }

    public static void dfs(char[] chars, int index, LinkedList<String> path, List<List<String>> ans) {
        if (index == chars.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            if (dp[index][i]) {
                path.add(String.valueOf(chars, index, i - index + 1));
                dfs(chars, i + 1, path, ans);
                path.removeLast();
            }
        }
    }

}
