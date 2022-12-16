package com.zzzj.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-11-24 17:57
 */
public class Leet1625 {

    public static void main(String[] args) {
        System.out.println(findLexSmallestString("5525", 9, 2));
        System.out.println(findLexSmallestString("43987654", 7, 3));
    }

    public static String findLexSmallestString(String s, int a, int b) {

        LinkedList<String> queue = new LinkedList<>();

        Set<String> visited = new HashSet<>();

        queue.add(s);

        int N = s.length();

        String ans = s;

        while (!queue.isEmpty()) {
            String first = queue.removeFirst();

            // +a
            StringBuilder copy = new StringBuilder(first);

            for (int i = 1; i < N; i += 2) {
                int num = (Character.digit(first.charAt(i), 10) + a) % 10;
                copy.setCharAt(i, Character.forDigit(num, 10));
            }

            String case1 = copy.toString();

            if (visited.add(case1)) {
                queue.add(case1);
            }

            // 移动b次
            // 5525 --2--> 2555
            String case2 = move(first, b);

            if (visited.add(case2)) {
                queue.add(case2);
            }

            if (first.compareTo(ans) < 0) {
                ans = first;
            }
        }

        return ans;
    }

    public static String move(String source, int count) {
        int len = source.length();

        StringBuilder builder = new StringBuilder(source);

        for (int i = 0; i < len; i++) {
            char c = source.charAt(i);
            builder.setCharAt((i + count) % len, c);
        }

        return builder.toString();
    }

}
