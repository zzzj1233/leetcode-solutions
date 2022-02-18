package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzzj
 * @create 2021-12-20 11:17
 */
public class Leet1297 {

    /**
     * 输入：s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
     * 输出：2
     * 解释：子串 "aab" 在原字符串中出现了 2 次。
     * 它满足所有的要求：2 个不同的字母，长度为 3 （在 minSize 和 maxSize 范围内）。
     */

    public static void main(String[] args) {
        String string = LeetUtils.newString(LeetUtils.random.nextInt(1000) + 5);
        int maxLetters = Math.max(1, LeetUtils.random.nextInt(27));
        int minSize = Math.max(1, LeetUtils.random.nextInt(27));
        int ans = violent(string, maxLetters, minSize, -1);
        int perhaps = maxFreq(string, maxLetters, minSize, -1);

        System.out.println("\"" + string + "\"");

        System.out.println(maxLetters);

        System.out.println(minSize);

        System.out.println(minSize + 1);

        System.out.println(maxFreq(string, maxLetters, minSize, -1));
    }

    private static int violent(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> counter = new HashMap<>(s.length());

        for (int i = 0; i < s.length() - minSize + 1; i++) {
            String candidate = s.substring(i, i + minSize);
            counter.put(candidate, counter.getOrDefault(candidate, 0) + 1);
        }

        int ans = 0;

        for (int i = 0; i < s.length() - minSize + 1; i++) {
            StringBuilder stringBuilder = new StringBuilder(minSize);
            stringBuilder.append(s.charAt(i));

            for (int j = 1; j < minSize; j++) {
                stringBuilder.append(s.charAt(j + i));
            }

            String subStr = stringBuilder.toString();

            Set<Character> table = new HashSet<>(minSize);

            for (int k = 0; k < subStr.length(); k++) {
                char c = subStr.charAt(k);
                table.add(c);
            }

            if (table.size() <= maxLetters) {
                ans = Math.max(ans, counter.getOrDefault(subStr, 0));
            }

        }
        return ans;
    }


    public static int maxFreq(String s, int maxLetters, int minSize, int maxSize) {

        Map<String, Integer> counter = new HashMap<>(s.length());

        for (int i = 0; i < s.length() - minSize + 1; i++) {
            String candidate = s.substring(i, i + minSize);
            counter.put(candidate, counter.getOrDefault(candidate, 0) + 1);
        }

        // 滑动窗口寻找子字符串
        int ans = 0;

        int l = 0;
        int r = 0;

        Map<Character, Integer> table = new HashMap<>();

        while (r < s.length()) {
            // 缩小窗口
            while (r - l >= minSize) {
                char c = s.charAt(l);
                Integer count = table.get(c);
                if (count == 1) {
                    table.remove(c);
                } else {
                    table.put(c, count - 1);
                }
                l++;
            }
            char c = s.charAt(r);
            table.put(c, table.getOrDefault(c, 0) + 1);

            if (table.size() <= maxLetters && r - l + 1 >= minSize) {
                ans = Math.max(ans, counter.getOrDefault(s.substring(l, r + 1), 0));
            }

            r++;
        }

        return ans;
    }

}
