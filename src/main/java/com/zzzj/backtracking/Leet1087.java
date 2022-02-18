package com.zzzj.backtracking;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-02-15 15:00
 */
public class Leet1087 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(expand("{a,b}{z,x,y}")));
    }

    // "{a,b}{z,x,y}"
    public static String[] expand(String s) {
        List<LinkedList<Character>> list = analyze(s);

        ArrayList<String> ans = new ArrayList<>();

        process(ans, list, 0, new char[list.size()]);

        String[] arr = new String[ans.size()];

        ans.toArray(arr);

        return arr;
    }

    public static void process(List<String> ans, List<LinkedList<Character>> list, int cur, char[] path) {
        if (cur == list.size()) {
            ans.add(String.valueOf(path));
            return;
        }

        LinkedList<Character> candidate = list.get(cur);

        for (Character c : candidate) {
            path[cur] = c;
            process(ans, list, cur + 1, path);
        }
    }

    public static List<LinkedList<Character>> analyze(String s) {

        boolean start = false;

        ArrayList<LinkedList<Character>> table = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == '{') {
                start = true;
                table.add(new LinkedList<>());
                continue;
            }

            if (start) {
                if (c == '}') {
                    start = false;
                    continue;
                }
                if (c == ',') {
                    continue;
                }
                table.get(table.size() - 1).add(c);
            } else {
                LinkedList<Character> list = new LinkedList<>();
                list.add(c);
                table.add(list);
            }
        }

        for (List<Character> list : table) {
            Collections.sort(list);
        }

        return table;
    }


}
