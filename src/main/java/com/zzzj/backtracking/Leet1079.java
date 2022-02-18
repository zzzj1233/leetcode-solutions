package com.zzzj.backtracking;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-02-11 15:13
 */
public class Leet1079 {

    public static void main(String[] args) {
        System.out.println(numTilePossibilities("AAB"));
        System.out.println(numTilePossibilities("AAABBCDE"));
    }

    public static int ans;

    /**
     * 输入："AAB"
     * 输出：8
     * 解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
     */
    public static int numTilePossibilities(String tiles) {
        ans = -1;

        char[] chars = tiles.toCharArray();

        Arrays.sort(chars);

        boolean[] used = new boolean[chars.length];

        process(chars, used);

        return ans;
    }

    public static void process(char[] chars, boolean[] used) {
        ans++;

        for (int i = 0; i < chars.length; i++) {
            if (used[i] || (i > 0 && chars[i] == chars[i - 1] && !used[i - 1])) {
                continue;
            }
            char c = chars[i];
            used[i] = true;
            process(chars, used);
            used[i] = false;
        }
    }

}
