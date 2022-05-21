package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-10 18:49
 */
public class Leet292 {

    public static void main(String[] args) {
        System.out.println(canWinNim(994521));
    }

    public static boolean canWinNim(int n) {
        return dfs(n);
    }

    // 1 ~ 3
    public static boolean dfs(int remain) {
        // 每次必拿 1 ~ 3个
        if (remain <= 3) {
            return true;
        }
        return !dfs(remain - 1) || !dfs(remain - 2) || !dfs(remain - 3);
    }

}
