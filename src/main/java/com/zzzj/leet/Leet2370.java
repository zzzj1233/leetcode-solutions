package com.zzzj.leet;

import com.zzzj.util.StringCopyIterator;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-10 16:42
 */
public class Leet2370 {


    public static void main(String[] args) {

        System.out.println(longestIdealString("ccacb", 15));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            String str = LeetUtils.randomString(5, "abcdef");

            StringCopyIterator it = new StringCopyIterator(str);

            int k = LeetUtils.random.nextInt(26);

            if (longestIdealString(it.next(), k) != right(it.next(), k)) {
                System.out.println("Error");
                System.out.println(LeetUtils.stringToLeetCode(it.next()));
                System.out.println(k);
                System.out.println("MyAns = " + longestIdealString(it.next(), k));
                System.out.println("RightAns = " + right(it.next(), k));
                return;
            }

        }

        System.out.println("OK~");
    }

    public static int longestIdealString(String s, int k) {
        int[] dp = new int[26];

        int N = s.length();

        for (int i = 0; i < N; i++) {
            int idx = s.charAt(i) - 'a';

            int max = -1;

            for (int j = 1; j <= k && idx + j < 26; j++) {
                max = Math.max(max, dp[idx + j]);
            }

            for (int j = Math.min(idx, k); j >= 0; j--) {
                max = Math.max(max, dp[idx - j]);
            }

            dp[idx] = max + 1;
        }

        return Arrays.stream(dp).max().orElse(0);
    }

    public static int right(String s, int k) {
        int[] dp = new int[26];
        int resultMax = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            int max = 0;
            for (int j = index - k < 0 ? 0 : index - k; j <= (index + k > 25 ? 25 : index + k); j++) {
                max = Math.max(dp[j], max);
            }
            dp[index] = max + 1;
            resultMax = Math.max(resultMax, dp[index]);
        }
        return resultMax;
    }
}
