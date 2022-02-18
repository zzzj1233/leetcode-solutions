package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author zzzj
 * @create 2021-12-24 18:14
 */
public class Leet1456 {

    public static void main(String[] args) {
        final Random random = LeetUtils.random;
        final String s = LeetUtils.newString(random.nextInt(15) + 2);
        int k = random.nextInt(s.length()) + 1;

        System.out.println("\"" + s + "\"");

        System.out.println(k);

        System.out.println(maxVowels(s, k));
    }

    public static int maxVowels(String s, int k) {
        int l = 0;
        int r = 0;


        int window = 0;

        while (r < k) {
            char c = s.charAt(r);
            if (isVowels(s.charAt(c))) {
                window++;
            }
            r++;
        }

        int ans = window;

        while (r < s.length()) {
            if (isVowels(s.charAt(l))) {
                window--;
            }
            if (isVowels(s.charAt(r))) {
                window++;
            }
            ans = Math.max(ans, window);
            r++;
            l++;
        }

        return ans;
    }

    private static boolean isVowels(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

}
