package com.zzzj.str;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2024-02-01 18:09
 */
public class Leet3008 {

    public static void main(String[] args) {

        System.out.println(beautifulIndices("ababababazzabababb", "aba", "bb", 10));

        System.out.println(beautifulIndices("isawsquirrelnearmysquirrelhouseohmy", "my", "squirrel", 15));

        System.out.println(beautifulIndices("abcd", "a", "a", 4));

    }

    public static List<Integer> beautifulIndices(String s, String a, String b, int k) {

        TreeSet<Integer> i1 = indexes(s, a);

        TreeSet<Integer> i2 = indexes(s, b);

        return i1.stream().filter(index -> {
            Integer ceiling = i2.ceiling(index);
            Integer floor = i2.floor(index);
            return (ceiling != null && ceiling - index <= k) || (floor != null && index - floor <= k);
        }).collect(Collectors.toList());
    }

    public static TreeSet<Integer> indexes(String source, String search) {

        int[] next = next(search);

        int N = source.length();

        int M = search.length();

        TreeSet<Integer> indexes = new TreeSet<>();

        int x = 0;

        int y = 0;

        while (x < N) {
            if (source.charAt(x) == search.charAt(y)) {
                x++;
                y++;
                if (y == M) {
                    indexes.add(x - M);
                    y = next[y];
                }
            } else if (next[y] >= 0) {
                y = next[y];
            } else {
                x++;
            }
        }

        return indexes;
    }

    public static int[] next(String str) {

        int N = str.length();

        if (N <= 1)
            return new int[]{-1, 0};

        int[] next = new int[N + 1];

        next[0] = -1;

        int index = 2;

        int cc = 0;

        while (index <= N)
            if (str.charAt(index - 1) == str.charAt(cc))
                next[index++] = ++cc;
            else if (next[cc] >= 0)
                cc = next[cc];
            else
                index++;

        return next;
    }

}
