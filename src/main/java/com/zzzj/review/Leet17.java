package com.zzzj.review;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-03-09 17:25
 */
public class Leet17 {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    static char[][] chars = new char[10][];

    static {
        chars[2] = new char[]{'a', 'b', 'c'};
        chars[3] = new char[]{'d', 'e', 'f'};
        chars[4] = new char[]{'g', 'h', 'i'};
        chars[5] = new char[]{'j', 'k', 'l'};
        chars[6] = new char[]{'m', 'n', 'o'};
        chars[7] = new char[]{'p', 'q', 'r', 's'};
        chars[8] = new char[]{'t', 'u', 'v'};
        chars[9] = new char[]{'w', 'x', 'y', 'z'};
    }

    public static List<String> letterCombinations(String digits) {

        int N = digits.length();

        List<String> ans = new ArrayList<>();

        if (N == 0) {
            return ans;
        }

        backtracking(0, digits, ans, new char[digits.length()], 0);

        return ans;
    }

    public static void backtracking(int index, String digits, List<String> ans, char[] path, int pathIndex) {
        if (index >= digits.length()) {
            ans.add(String.valueOf(path));
            return;
        }

        char[] cs = chars[Character.digit(digits.charAt(index), 10)];

        for (char c : cs) {

            path[pathIndex] = c;

            backtracking(index + 1, digits, ans, path, pathIndex + 1);
        }

    }

}
