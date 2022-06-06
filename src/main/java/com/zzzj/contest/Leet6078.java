package com.zzzj.contest;

/**
 * @author Zzzj
 * @create 2022-05-29 14:56
 */
public class Leet6078 {

    public static void main(String[] args) {
        System.out.println(rearrangeCharacters("zxzhanhavd", "ahz"));
    }

    public static int rearrangeCharacters(String s, String target) {
        int[] table1 = new int[26];
        int[] table2 = new int[26];

        for (int i = 0; i < s.length(); i++) {
            table1[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < target.length(); i++) {
            table2[target.charAt(i) - 'a']++;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < 26; i++) {
            if (table2[i] > 0 && table1[i] > 0) {
                ans = Math.min(ans, table1[i] / table2[i]);
            }
        }

        return ans;
    }


}
