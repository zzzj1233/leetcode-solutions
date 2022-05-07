package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-05-06 17:35
 */
public class Leet293 {

    public static void main(String[] args) {
        System.out.println(generatePossibleNextMoves("++++"));
    }

    public static final char PLUS = '+';

    public static final char SUB = '-';

    public static List<String> generatePossibleNextMoves(String currentState) {
        if (currentState == null || currentState.length() < 2) {
            return Collections.emptyList();
        }

        List<String> ans = new ArrayList<>();

        dfs(ans, currentState.toCharArray(), 0);

        return ans;
    }

    public static void dfs(List<String> ans, char[] chars, int i) {
        if (chars.length - i < 2) {
            return;
        }

        // 可以反转
        if (chars[i] == PLUS && chars[i + 1] == PLUS) {
            StringBuilder builder = new StringBuilder(String.valueOf(chars));
            builder.setCharAt(i, SUB);
            builder.setCharAt(i + 1, SUB);
            ans.add(builder.toString());
        }

        dfs(ans, chars, i + 1);
    }

}
