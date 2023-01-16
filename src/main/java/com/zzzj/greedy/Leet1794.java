package com.zzzj.greedy;

public class Leet1794 {

    public static int countQuadruples(String firstString, String secondString) {

        int[] tab = new int[26];

        for (int i = 0; i < secondString.length(); i++) {
            char c = secondString.charAt(i);

            int index = c - 'a';

            tab[index]++;
        }

        int ans = 0;

        for (int i = 0; i < firstString.length(); i++) {
            char c = firstString.charAt(i);

            ans += tab[c - 'a'];
        }

        return ans;
    }

}
