package com.zzzj.greedy;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-09-20 11:08
 */
public class Leet1081 {

    public static void main(String[] args) {
        System.out.println(smallestSubsequence("cbacdcbc"));
    }

    public static String smallestSubsequence(String s) {

        int N = s.length();

        int[] count = new int[26];

        for (int i = 0; i < N; i++) {
            count[s.charAt(i) - 'a']++;
        }

        LinkedList<Character> stack = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);

            if (stack.contains(c)) {
                count[c - 'a']--;
                continue;
            }

            while (!stack.isEmpty()) {
                Character last = stack.peekLast();
                if (count[last - 'a'] > 0 && c < last) {
                    stack.removeLast();
                } else {
                    break;
                }
            }
            stack.add(c);
            count[c - 'a']--;
        }

        StringBuilder builder = new StringBuilder(stack.size());

        stack.forEach(builder::append);

        return builder.toString();
    }


}
