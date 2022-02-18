package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-02-16 10:03
 */
public class Leet267 {

    public static void main(String[] args) {
        // System.out.println(generatePalindromes("aabbccddeeffgghh"));
        System.out.println(generatePalindromes("aabb"));
        System.out.println(generatePalindromes("aab"));
        System.out.println(generatePalindromes("a"));
        System.out.println(generatePalindromes("abc"));
    }

    public static List<String> generatePalindromes(String s) {
        List<String> ans = new ArrayList<>();

        char[] chars = s.toCharArray();

        Arrays.sort(chars);

        boolean isOdd = chars.length % 2 != 0;

        process(ans, chars, new char[s.length()], new boolean[s.length()], 0, isOdd);

        return ans;
    }

    public static boolean isPermutation(char[] path, int cur, int mid, boolean isOdd) {
        if (path[cur] != path[mid - (cur - mid) - (isOdd ? 2 : 1)]) {
            return false;
        }

        return true;
    }

    public static void process(List<String> ans, char[] chars, char[] path, boolean[] used, int cur, boolean isOdd) {

        int mid = (chars.length + 1) / 2;

        if (cur > (chars.length + 1) / 2 && !isPermutation(path, cur - 1, mid, isOdd)) {
            // 是否是回文
            return;
        }

        if (cur == chars.length) {
            ans.add(String.valueOf(path));
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (used[i] || (i > 0 && chars[i] == chars[i - 1] && !used[i - 1])) {
                continue;
            }
            path[cur] = chars[i];
            used[i] = true;
            process(ans, chars, path, used, cur + 1, isOdd);
            used[i] = false;
        }

    }

}
