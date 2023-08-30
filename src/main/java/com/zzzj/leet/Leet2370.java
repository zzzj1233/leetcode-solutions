package com.zzzj.leet;

import com.zzzj.util.StringCopyIterator;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-10 16:42
 */
public class Leet2370 {


    public static void main(String[] args) {

//        System.out.println(longestIdealString("ccacb", 15));
//        System.out.println(right("ccacb", 15));
//        System.out.println(longestIdealString("acfgbd", 2));
//        System.out.println(longestIdealString("abcd", 3));
//
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

        int N = s.length();

        int[] dp = new int[26];

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int index = s.charAt(i) - 'a';

            int[] temp = Arrays.copyOfRange(dp, 0, dp.length);

            for (int j = 0; j <= k; j++) {
                if (index - j < 0) break;
                temp[index] = Math.max(temp[index], dp[index - j] + 1);
            }

            for (int j = 0; j <= k; j++) {
                if (index + j > 25) break;
                temp[index] = Math.max(temp[index], dp[index + j] + 1);
            }

            dp = temp;
            ans = Math.max(ans, dp[index]);
        }

        return ans;
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
