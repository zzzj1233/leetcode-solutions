package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

/**
 * @author Zzzj
 * @create 2022-06-19 21:44
 */
public class Leet926 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            String str = LeetUtils.randomString(100, "01");
            if (minFlipsMonoIncr(str) != right(str)) {
                System.out.println("Error");
                System.out.println(str);
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int minFlipsMonoIncr(String s) {
        int N = s.length();

        int[] zero = new int[N + 1];
        int[] one = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            zero[i] = zero[i - 1] + (s.charAt(i - 1) != '0' ? 1 : 0);
        }

        for (int i = N - 1; i >= 0; i--) {
            one[i] = one[i + 1] + (s.charAt(i) != '1' ? 1 : 0);
        }

        int ans = Math.min(zero[N], one[0]);

        for (int i = 0; i < N; i++) {
            ans = Math.min(ans, zero[i] + one[i]);
        }

        return ans;
    }

    public static int right(String s) {
        int t = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            t += arr[i] == '1' ? 1 : 0;
        }
        int ans = t;
        for (int i = arr.length - 1; i >= 0; i--) {
            t += arr[i] == '0' ? 1 : -1;
            ans = Math.min(ans, t);
        }
        return ans;
    }

}
