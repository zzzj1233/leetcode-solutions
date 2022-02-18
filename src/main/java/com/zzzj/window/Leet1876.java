package com.zzzj.window;

/**
 * @author zzzj
 * @create 2021-12-15 10:56
 */
public class Leet1876 {

    public static void main(String[] args) {
        System.out.println(countGoodSubstrings("xyzzaz"));
    }

    public static int countGoodSubstrings(String s) {
        if (s == null || s.length() < 3) {
            return 0;
        }

        int answer = 0;

        int[] table = new int[26];

        boolean firstMatch = true;

        int expired = 0;

        for (int i = 0; i <= 2; i++) {
            char c = s.charAt(i);
            int index = c - 97;
            if (table[index] != 0) {
                firstMatch = false;
            }
            table[index]++;
        }

        expired = (int) s.charAt(0) - 97;

        for (int i = 3; i < s.length(); i++) {
            table[expired]--;
            char c = s.charAt(i);
            int index = c - 97;
            table[index]++;
            if (match(table)) {
                answer++;
            }
            expired = (int) s.charAt(i - 2) - 97;
        }

        return answer + (firstMatch ? 1 : 0);
    }

    private static boolean match(int[] table) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] > 1) {
                return false;
            }
        }
        return true;
    }

}
