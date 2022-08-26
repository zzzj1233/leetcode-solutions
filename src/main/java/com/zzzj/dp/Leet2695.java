package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-08-25 16:23
 */
public class Leet2695 {

    public static void main(String[] args) {
        System.out.println(countEval("1^0|0|1", 0));
    }

    public static int countEval(String s, int result) {
        return dfs(s, result == 1, s.charAt(0) == '1', 1);
    }

    public static int dfs(String s, boolean result, boolean cur, int index) {
        if (index >= s.length()) {
            return cur == result ? 1 : 0;
        }

        return -1;
    }

}
