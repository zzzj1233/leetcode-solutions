package com.zzzj.backtracking;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-02-28 16:11
 */
public class Leet301 {

    public static void main(String[] args) {
        String str = "abcdefghijklmnopqrstuvwxyz()";

        Leet301Right right = new Leet301Right();

        for (int i = 0; i < 10000; i++) {
            Leet301Right.res.clear();
            String s = LeetUtils.randomString0(str, LeetUtils.random.nextInt(26));
            if (!new HashSet<>(removeInvalidParentheses(s)).equals(new HashSet<>(right.removeInvalidParentheses(s)))) {
                System.out.println(s);
                System.out.println(removeInvalidParentheses(s));
                return;
            }
        }

    }

    public static final char LEFT = '(';

    public static final char RIGHT = ')';

    public static final char REMOVED = 'A';


    public static List<String> removeInvalidParentheses(String s) {
        char[] chars = s.toCharArray();

        if (isValid(chars)) {
            return Collections.singletonList(s);
        }

        Set<String> ans = new HashSet<>();

        for (int i = 1; i < s.length(); i++) {
            process(chars, ans, i, 0);
            if (!ans.isEmpty()) {
                return new ArrayList<>(ans);
            }
        }

        return Collections.singletonList("");
    }


    public static void process(char[] path, Set<String> ans, int allow, int cur) {
        if (allow == 0) {
            if (isValid(path)) {
                // add answer
                ans.add(resolveAns(path));
            }
            return;
        }

        for (int i = cur; i < path.length; i++) {
            if (path[i] == REMOVED || (path[i] != LEFT && path[i] != RIGHT)) {
                continue;
            }
            if (i > 0 && path[i] == path[i - 1]) {
                continue;
            }
            char c = path[i];
            path[i] = REMOVED;
            process(path, ans, allow - 1, cur + 1);
            path[i] = c;
        }
    }


    public static String resolveAns(char[] path) {
        char[] newPath = new char[path.length];

        int index = 0;

        for (int i = 0; i < path.length; i++) {
            if (path[i] == REMOVED) {
                continue;
            }
            newPath[index++] = path[i];
        }

        return index == 0 ? "" : String.valueOf(newPath).trim();
    }

    public static boolean isValid(char[] chars) {
        int count = 0;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == LEFT) {
                count++;
            } else if (c == RIGHT) {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }

        return count == 0;
    }

}
