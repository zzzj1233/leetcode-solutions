package com.zzzj.dp;

/**
 * @author Zzzj
 * @create 2022-07-09 14:27
 * <p>
 * <p>
 * 真的很难~
 */
public class Leet887 {

    public static void main(String[] args) {
        System.out.println(superEggDrop(2, 6));
    }

    public static int superEggDrop(int k, int n) {
        return dfs(k, n);
    }

    public static int dfs(int k, int n) {
        if (n == 0) {
            return 0;
        }

        if (k == n) {
            return n;
        }

        int result = n;

        for (int i = 1; i <= n; i++) {
            result = Math.min(Math.max(dfs(k - 1, i - 1), dfs(k, n - i)) + 1, result);
        }

        return result;
    }


}
