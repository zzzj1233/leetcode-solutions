package com.zzzj.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-02-14 14:46
 */
public class Leet1593 {

    public static void main(String[] args) {
        System.out.println(maxUniqueSplit("aba"));
        System.out.println(maxUniqueSplit("ababccc"));
    }


    public static int ans;

    public static int maxUniqueSplit(String s) {
        ans = 0;

        char[] chars = s.toCharArray();

        process(chars, new HashSet<>(), 0);

        return ans;
    }

    public static void process(char[] chars, Set<String> used, int cur) {
        if (cur == chars.length) {
            ans = Math.max(ans, used.size());
            return;
        }

        char c = chars[cur];
        // 如果当前元素不存在
        // 那么可以要当前元素

        StringBuilder builder = new StringBuilder();
        for (int i = cur; i < chars.length; i++) {
            builder.append(chars[i]);
            String s = builder.toString();
            if (used.add(s)) {
                process(chars, used, i + 1);
                used.remove(s);
            }
        }

    }

}
