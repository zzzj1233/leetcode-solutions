package com.zzzj.leet;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-05-12 15:02
 */
public class Leet316 {

    public static void main(String[] args) {
        // "abacb"
        //  acb -> abc
        System.out.println(removeDuplicateLetters("bbcaac"));
//        System.out.println(removeDuplicateLetters("cbacdcbccc"));
//        System.out.println(removeDuplicateLetters("bcabc"));
//        System.out.println(removeDuplicateLetters("zbcabcz"));
//        System.out.println(removeDuplicateLetters("zbcabczc"));
    }

    // "bbcaac"
    //  ac
    //  bac
    public static String removeDuplicateLetters(String s) {
        int[] table = new int[26];

        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }

        LinkedList<Character> stack = new LinkedList<>();

        boolean[] visited = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (visited[c - 'a']) {
                table[c - 'a']--;
                continue;
            }
            visited[c - 'a'] = true;
            while (!stack.isEmpty()) {
                Character lastChar = stack.peekLast();
                if (c < lastChar && table[lastChar - 'a'] > 1) {
                    table[lastChar - 'a']--;
                    visited[lastChar - 'a'] = false;
                    stack.removeLast();
                } else {
                    break;
                }
            }
            stack.add(c);
        }

        StringBuilder builder = new StringBuilder(stack.size());

        Iterator<Character> iterator = stack.iterator();

        while (iterator.hasNext()) {
            final Character character = iterator.next();
            builder.append(character);
        }


        return builder.toString();
    }

}
