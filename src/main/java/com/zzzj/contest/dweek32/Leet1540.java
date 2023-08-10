package com.zzzj.contest.dweek32;

/**
 * @author zzzj
 * @create 2023-08-09 15:21
 */
public class Leet1540 {

    public static void main(String[] args) {

        System.out.println(canConvertString("atmtxzjkz", "tvbtjhvjd", 35));

    }


    public static boolean canConvertString(String s, String t, int k) {

        int N = s.length();

        if (s.length() != t.length()) return false;

        int[] used = new int[27];

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == t.charAt(i)) continue;

            char c1 = s.charAt(i);

            char c2 = t.charAt(i);

            int diff = Math.abs(c1 - c2);

            System.out.println(diff);

            int old = used[diff];

            if (26 * old + diff > k) return false;

            used[diff]++;
        }

        return true;
    }


}
