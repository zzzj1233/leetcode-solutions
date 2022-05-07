package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-06 17:44
 */
public class Leet294 {

    public static void main(String[] args) {
        System.out.println(canWin("++++"));
    }

    public static final char PLUS = '+';

    public static final char SUB = '-';

    public static boolean canWin(String currentState) {
        char[] chars = currentState.toCharArray();
        return dfs(currentState.toCharArray());
    }

    public static boolean dfs(char[] chars) {
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == PLUS && chars[i + 1] == PLUS) {
                chars[i] = SUB;
                chars[i + 1] = SUB;
                boolean ans = dfs(chars);
                chars[i] = PLUS;
                chars[i + 1] = PLUS;
                if (!ans) {
                    return true;
                }
            }
        }
        return false;
    }

}
