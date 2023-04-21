package com.zzzj.str;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-04-13 15:35
 */
public class Leet2559 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(vowelStrings(
                new String[]{"aba", "bcb", "ece", "aa", "e"},
                LeetUtils.convertInts("[[0,2],[1,4],[1,1]]")
        )));

        System.out.println(Arrays.toString(vowelStrings(
                new String[]{"a", "e", "i"},
                LeetUtils.convertInts("[[0,2],[0,1],[2,2]]")
        )));

    }


    public static int[] vowelStrings(String[] words, int[][] queries) {

        char[] chars = {'a', 'e', 'i', 'o', 'u'};

        int N = words.length;

        int[] preSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + (is(chars, words[i - 1]) ? 1 : 0);
        }

        int M = queries.length;

        int[] ans = new int[M];

        for (int i = 0; i < M; i++) {
            ans[i] = preSum[queries[i][1] + 1] - preSum[queries[i][0]];
        }

        return ans;
    }

    public static boolean is(char[] chars, String word) {
        char c1 = word.charAt(0);
        char c2 = word.charAt(word.length() - 1);

        boolean b1 = false;
        boolean b2 = false;

        for (char c : chars) {
            b1 |= c1 == c;
            b2 |= c2 == c;
        }

        return b1 && b2;
    }

}
