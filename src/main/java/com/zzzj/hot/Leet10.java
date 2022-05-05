package com.zzzj.hot;

/**
 * @author zzzj
 * @create 2022-04-24 17:19
 */
public class Leet10 {

    public static boolean isMatch(String s, String p) {
        if (!isValidPattern(p)) {
            return false;
        }
        return dfs(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    public static boolean isValidPattern(String p) {
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && (i == 0 || p.charAt(i - 1) == '*')) {
                return false;
            }
        }
        return true;
    }

    public static boolean dfs(char[] s, char[] p, int s1, int pi) {

        return false;
    }

}
