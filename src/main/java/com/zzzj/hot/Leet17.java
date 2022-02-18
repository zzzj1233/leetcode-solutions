package com.zzzj.hot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-01-24 12:24
 */
public class Leet17 {

    public static final char[][] CHARS = {
            {},
            {},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public static void main(String[] args) {
        System.out.println(letterCombinations("2345"));
    }

    public static List<String> letterCombinations(String digits) {

        if (digits.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> ans = new ArrayList<>();

        process(ans, 0, digits, new char[digits.length()]);

        return ans;
    }

    public static void process(List<String> ans, int index, String digits, char[] path) {
        if (index == digits.length()){
            ans.add(String.valueOf(path));
            return;
        }

        int idx = Character.digit(digits.charAt(index), 10);

        for (char c : CHARS[idx]) {
            path[index] = c;
            process(ans, index + 1, digits, path);
        }

    }

}
