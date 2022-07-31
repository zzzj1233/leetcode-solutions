package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-18 18:54
 */
public class Leet439 {

    public static void main(String[] args) {
        System.out.println(parseTernary("T?T?F:5:3"));
        System.out.println(parseTernary("T?2:3"));
        System.out.println(parseTernary("F?1:T?4:5"));
        System.out.println(parseTernary("F?T:F?T?1:2:F?3:4"));
    }

    public static String parseTernary(String expression) {
        return String.valueOf(dfs(expression, 0, expression.length() - 1));
    }

    public static char dfs(String exp, int left, int right) {
        char c = exp.charAt(left);

        boolean hasQuestion = left + 1 < exp.length() && exp.charAt(left + 1) == '?';

        if (hasQuestion) {
            int index = findNextColon(exp, left + 1);
            if (c == 'T') {
                return dfs(exp, left + 2, index - 1);
            } else if (c == 'F') {    // 右边
                return dfs(exp, index + 1, right);
            }
        }

        return c;
    }

    public static int findNextColon(String expression, int start) {
        int colonCount = 0;

        for (int i = start + 1; i < expression.length(); i++) {
            if (expression.charAt(i) == ':') {
                if (colonCount == 0) {
                    return i;
                }
                colonCount--;
            } else if (expression.charAt(i) == '?') {
                colonCount++;
            }
        }

        return -1;
    }


}
