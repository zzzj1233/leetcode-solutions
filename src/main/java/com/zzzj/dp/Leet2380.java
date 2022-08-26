package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-15 16:16
 */
public class Leet2380 {

    public static void main(String[] args) {

    }

    public static int secondsToRemoveOccurrences(String s) {
        int N = s.length();

        int ans = 0;

        while (s.contains("01")) {
            ans++;
            s = s.replaceAll("01", "10");
        }

        return ans;
    }

}
