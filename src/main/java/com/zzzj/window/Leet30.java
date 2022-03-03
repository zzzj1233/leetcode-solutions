package com.zzzj.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2022-02-20 15:20
 */
public class Leet30 {

    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));
        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();

        // 窗口大小
        int window = words[0].length() * words.length;

        // 当前单词
        char[] path = new char[words[0].length()];


        Map<String, Integer> table = new HashMap<>(words.length);

        for (String word : words) {
            table.put(word, table.getOrDefault(word, 0) + 1);
        }

        int l = 0;

        int r = 0;


        while (l < s.length()) {

            Map<String, Integer> curTab = new HashMap<>(words.length);

            while (r < s.length() && r - l < window) {
                char c = s.charAt(r);

                int pathIndex = (r - l) % path.length;

                path[pathIndex] = c;

                // 一个单词
                if (pathIndex == path.length - 1) {
                    String pathStr = String.valueOf(path);
                    Integer count = table.get(pathStr);
                    int curCount = curTab.getOrDefault(pathStr, 0);

                    if (count == null || curCount + 1 > count) {
                        l++;
                        r = l;
                        curTab.clear();
                        continue;
                    } else {
                        if (r - l == window - 1) {
                            ans.add(l);
                            curTab.clear();
                        } else {
                            curTab.put(pathStr, curCount + 1);
                        }
                    }
                }

                r++;
            }

            l++;
            r = l;
        }


        return ans;
    }

}
