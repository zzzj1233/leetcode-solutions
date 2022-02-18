package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-02-16 11:09
 */
public class Leet320 {

    public static void main(String[] args) {

        Set<String> right = new HashSet<>(right("dictionaryabcd"));

        HashSet<String> ans = new HashSet<>(generateAbbreviations("dictionaryabcd"));


        right.removeAll(ans);

        System.out.println(right);

    }

    public static List<String> generateAbbreviations(String word) {
        List<String> ans = new ArrayList<>();

        process(ans, 0, new StringBuilder(), word.toCharArray(), true);

        return ans;
    }

    public static void process(List<String> ans, int cur,
                               StringBuilder builder,
                               char[] chars,
                               boolean allow) {

        if (cur == chars.length) {
            ans.add(new String(builder));
            return;
        }

        int len = builder.length();

        if (allow) {
            for (int i = 0; i <= chars.length - cur; i++) {
                if (i > 0) {
                    builder.append(i);
                    process(ans, cur + i, builder, chars, false);
                    builder.setLength(len);
                }
            }
        }

        builder.append(chars[cur]);

        process(ans, cur + 1, builder, chars, true);

        builder.setLength(len);
    }

    public static List<String> right(String word) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), word, 0, 0);
        return ans;
    }

    // i is the current position
    // k is the count of consecutive abbreviated characters
    private static void backtrack(List<String> ans, StringBuilder builder, String word, int i, int k) {
        int len = builder.length(); // keep the length of builder
        if (i == word.length()) {
            if (k != 0) builder.append(k); // append the last k if non zero
            ans.add(builder.toString());
        } else {
            // the branch that word.charAt(i) is abbreviated
            backtrack(ans, builder, word, i + 1, k + 1);

            // the branch that word.charAt(i) is kept
            if (k != 0) builder.append(k);
            builder.append(word.charAt(i));
            backtrack(ans, builder, word, i + 1, 0);
        }
        builder.setLength(len); // reset builder to the original state
    }


}
