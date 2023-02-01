package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.CopyableIterator;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-11-03 17:22
 */
public class Leet2131 {

    public static void main(String[] args) {

        for (int i = 0; ; i++) {
            int N = 6;

            String[] arr = new String[N];

            for (int j = 0; j < N; j++) {
                arr[j] = LeetUtils.randomString(2, false);
                if (LeetUtils.random.nextBoolean()){
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
        // 1. 相反字符串
        // 2.
        return -1;
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
