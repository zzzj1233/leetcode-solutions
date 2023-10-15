package com.zzzj.contest.dweek112;

import java.util.Arrays;

public class Question2 {

    public static void main(String[] args) {

        // "bnxw"
        //"bwxn"
        System.out.println(checkStrings("bnxw", "bwxn"));

        System.out.println(checkStrings("abcdba", "cabdab"));

        System.out.println(checkStrings("abe", "bea"));

    }

    public static boolean checkStrings(String s1, String s2) {

        if (s1.equals(s2)) return true;

        int N = s1.length();

        int[] odd1 = new int[26];
        int[] even1 = new int[26];

        int[] odd2 = new int[26];
        int[] even2 = new int[26];

        for (int i = 0; i < N; i++) {

            int idx1 = s1.charAt(i) - 'a';

            int idx2 = s2.charAt(i) - 'a';

            if (i % 2 == 0) {
                even1[idx1]++;
                even2[idx2]++;
            } else {
                odd1[idx1]++;
                odd2[idx2]++;
            }
        }

        return Arrays.equals(odd1, odd2) && Arrays.equals(even1, even2);
    }

}
