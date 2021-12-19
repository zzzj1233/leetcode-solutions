package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2021-12-19 20:57
 */
public class Leet76 {

    public static void main(String[] args) {
        String s = LeetUtils.randomString(50, false);
        String t = LeetUtils.randomString(3, false);

//        System.out.println(s);
//        System.out.println(t);

        // System.out.println(minWindow(s, t));
        System.out.println(minWindow("AOABC", "ABC"));
    }

    // AOABC
    // ABC
    public static String minWindow(String s, String t) {

        if (t.length() > s.length()) {
            return "";
        }

        Map<Character, Integer> map = new HashMap<>(t.length());

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int l = -1;

        int ansL = 0;
        int ansR = Integer.MAX_VALUE;

        int count = t.length();

        for (int r = 0; r < s.length(); r++) {

            char c = s.charAt(r);

            if (!map.containsKey(c)) {
                continue;
            }

            remove(map, c);
            count--;

            // 存在这个字符
            if (l == -1) {
                l = r;
            }

            // 缩小窗口,缩小到不满足条件为止
            while (count == 0) {

                if (r - l < ansR - ansL) {
                    ansR = r;
                    ansL = l;
                }

                char c2 = s.charAt(l);

                if (map.containsKey(c2)) {
                    remove(map, c2);
                    if (map.get(c2) > 0) {
                        count++;
                    }
                }

                l++;

                while (l < s.length() && !map.containsKey(s.charAt(l))) {
                    l++;
                }

            }

        }

        return ansR == Integer.MAX_VALUE ? "" : s.substring(ansL, ansR + 1);
    }


    private static void remove(Map<Character, Integer> record, Character c) {
        Integer count = record.get(c);
        if (count > 1) {
            record.put(c, count - 1);
        } else {
            record.remove(c);
        }
    }

    private static boolean allMatch(Map<Character, Integer> map, Map<Character, Integer> record) {
        if (map.size() != record.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Integer count = record.get(entry.getKey());
            if (count == null || count < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

}
