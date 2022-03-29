package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-03-29 10:43
 */
public class Leet2512 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(permutation("qqe")));
        System.out.println(Arrays.toString(permutation("ab")));
    }

    public static String[] permutation(String S) {
        char[] chars = S.toCharArray();

        Arrays.sort(chars);

        ArrayList<String> list = new ArrayList<>();

        dfs(list, chars, new boolean[chars.length], new char[chars.length], 0);

        return list.toArray(new String[0]);
    }

    public static void dfs(List<String> list, char[] chars, boolean[] used, char[] path, int index) {
        if (index == chars.length) {
            list.add(String.valueOf(path));
            return;
        }

        // qqe
        for (int i = 0; i < chars.length; i++) {
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) {
                continue;
            }
            if (used[i]){
                continue;
            }
            path[index] = chars[i];
            used[i] = true;
            dfs(list, chars, used, path, index + 1);
            used[i] = false;
        }

    }

}
