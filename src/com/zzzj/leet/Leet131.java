package com.zzzj.leet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-10-18 14:51
 */
public class Leet131 {

    private static List<List<String>> result = new ArrayList<>();

    public static List<List<String>> partition(String s) {
        partition0(s, 0, new LinkedList<>());

        return result;
    }

    private static void partition0(String s, int start, LinkedList<String> path) {
        if (start == s.length()) {
            result.add(path);
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (i == start || isPalindrome(s, start, i)) {
                // 回文字符串
                LinkedList<String> newPath = new LinkedList<>(path);
                newPath.add(s.substring(start, i + 1));
                partition0(s, i + 1, newPath);
            }
        }
    }

    private static boolean isPalindrome(String s, int start, int end) {
        int i = start;
        int j = end;

        while (i <= j) {
            char a = s.charAt(i);
            char b = s.charAt(j);
            if (a != b) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
