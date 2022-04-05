package com.zzzj.hot;

/**
 * @author Zzzj
 * @create 2022-04-05 20:25
 */
public class Leet76 {


    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {

        int all = t.length();

        int ansL = -1;
        int ansR = -1;

        int l = 0;
        int r = 0;

        int[] table = new int[256];

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            table[c]++;
        }

        while (r < s.length()) {
            char c = s.charAt(r);

            if (table[c] > 0) {
                all--;
            }

            table[c]--;

            if (all == 0) {
                // 缩小L
                while (table[s.charAt(l)] < 0) {
                    table[s.charAt(l)]++;
                    l++;
                }

                if (ansL == -1 || r - l < ansR - ansL) {
                    ansL = l;
                    ansR = r;
                }
            }

            r++;
        }


        return ansL == -1 ? "" : s.substring(ansL, ansR + 1);
    }

}
