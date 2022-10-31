package com.zzzj.leet;

import java.util.HashSet;

public class Leet2405 {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            String str = LeetUtils.randomString(5, false);
            if (partitionString(str) != right(str)) {
                System.out.println(str);
                System.out.println(partitionString(str));
                System.out.println(right(str));
                return;
            }
        }
    }

    public static int partitionString(String s) {
        boolean[] window = new boolean[26];

        int N = s.length();

        int ans = 0;

        int L = 0;

        int R = 0;

        while (R < N) {
            char c = s.charAt(R);

            int idx = c - 'a';

            if (window[idx]) {
                while (L < R) {
                    window[s.charAt(L) - 'a'] = false;
                    L++;
                }
                ans++;
            }

            window[idx] = true;

            R++;
        }

        return ans + 1;
    }


    public static int right(String s) {
        int count = 1;
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (hashSet.contains(s.charAt(i))) {
                count++;
                hashSet.clear();
                hashSet.add(s.charAt(i));
            }
            hashSet.add(s.charAt(i));
        }
        return count;
    }

}
