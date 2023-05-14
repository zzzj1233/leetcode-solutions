package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.CopyableIterator;

import java.time.OffsetDateTime;
import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-11-03 17:22
 */
public class Leet2131 {

    public static void main(String[] args) {
//
//        System.out.println(longestPalindrome(new String[]{"lc", "cl", "gg"}));
//
//        System.out.println(longestPalindrome(new String[]{"ab", "ty", "yt", "lc", "cl", "ab"}));
//
//        System.out.println(longestPalindrome(new String[]{"cc", "ll", "xx"}));
//
//        System.exit(0);

        for (int i = 0; ; i++) {
            int N = 6;

            String[] arr = new String[N];

            for (int j = 0; j < N; j++) {
                arr[j] = LeetUtils.randomString(2, false);
                if (LeetUtils.random.nextBoolean()) {
                    StringBuilder builder = new StringBuilder(arr[j]);
                    builder.setCharAt(1, builder.charAt(0));
                    arr[j] = builder.toString();
                }
            }

            CopyableIterator<String[]> iterator = new CopyableIterator<>(arr, strings -> Arrays.copyOfRange(strings, 0, strings.length));

            if (longestPalindrome(iterator.next()) != right(iterator.next())) {
                System.out.println("Error");
                System.out.println(LeetUtils.stringsToLeetCode(iterator.next()));
                System.out.println("MyAns : " + longestPalindrome(iterator.next()));
                System.out.println("Right : " + right(iterator.next()));
                return;
            }

        }

        // System.out.println("Ok");
    }

    public static int longestPalindrome(String[] words) {

        int[][] tab = new int[26][26];

        int ans = 0;

        for (String word : words) {

            int c1 = word.charAt(0) - 'a';

            int c2 = word.charAt(1) - 'a';

            // contains reversal

            if (tab[c2][c1] > 0) {
                tab[c2][c1]--;
                ans += 4;
            } else {
                tab[c1][c2]++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (tab[i][i] > 0) {
                ans += 2;
                break;
            }
        }

        return ans;
    }


    public static int right(String[] words) {
        char[][] ch = new char[26][26];
        int c1, c2;
        int l = 0;
        for (String w : words) {
            c1 = w.charAt(0) - 'a';
            c2 = w.charAt(1) - 'a';
            if (ch[c2][c1] > 0) {
                ch[c2][c1]--;
                l += 4;
                continue;
            }
            ch[c1][c2]++;
        }

        for (int i = 0; i < 26; i++) {
            if (ch[i][i] > 0) {
                l += 2;
                break;
            }
        }

        return l;
    }

}
