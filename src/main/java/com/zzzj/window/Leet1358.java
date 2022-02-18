package com.zzzj.window;

/**
 * @author zzzj
 * @create 2021-12-20 16:18
 */
public class Leet1358 {

    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("abcabc"));
        System.out.println(numberOfSubstrings("aaacb"));
        System.out.println(numberOfSubstrings("abc"));
    }

    public static int numberOfSubstrings(String s) {

        int l = 0;

        int ans = 0;

        while (l < s.length()) {
            int r = l;
            int[] table = new int[3];
            while (r < s.length()) {
                table[s.charAt(r) - 'a']++;
                if (match(table)) {
                    ans += s.length() - r;
                    break;
                }
                r++;
            }
            l++;
        }

        return ans;
    }

    private static boolean match(int[] table) {
        return table[0] > 0 && table[1] > 0 && table[2] > 0;
    }

}
