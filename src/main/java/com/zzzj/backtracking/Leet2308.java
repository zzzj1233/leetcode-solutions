package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-03-29 12:02
 */
public class Leet2308 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(permutation("abcdef")));
    }

    public static String[] permutation(String s) {
        ArrayList<String> ans = new ArrayList<>();

        final char[] chars = s.toCharArray();

        Arrays.sort(chars);

        dfs(ans, chars, new boolean[s.length()], new char[s.length()], 0);

        return ans.toArray(new String[0]);
    }

    public static void dfs(List<String> ans, char[] chars, boolean[] used, char[] path, int index) {
        if (index == chars.length) {
            ans.add(String.valueOf(path));
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) {
                continue;
            }
            path[index] = chars[i];
            used[i] = true;
            dfs(ans, chars, used, path, index + 1);
            used[i] = false;
        }
    }

}
