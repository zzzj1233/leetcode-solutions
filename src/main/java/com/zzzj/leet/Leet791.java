package com.zzzj.leet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-09-13 16:39
 */
public class Leet791 {

    public static void main(String[] args) {
        System.out.println(customSortString("cba", "abcd"));
        System.out.println(customSortString("cbafg", "abcd"));
    }

    public static String customSortString(String order, String s) {

        int[] weight = new int[26];

        int N = order.length();

        for (int i = 0; i < N; i++) {
            char c = order.charAt(i);
            weight[c - 'a'] = N - i;
        }

        Character[] chars = new Character[s.length()];

        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }

        List<Character> collect = Arrays.stream(chars).sorted((o1, o2) -> weight[o2 - 'a'] - weight[o1 - 'a'])
                .collect(Collectors.toList());

        char[] arr = new char[s.length()];

        for (int i = 0; i < collect.size(); i++) {
            arr[i] = collect.get(i);
        }

        return String.valueOf(arr);
    }

}
