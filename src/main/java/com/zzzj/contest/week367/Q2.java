package com.zzzj.contest.week367;

public class Q2 {

    public static void main(String[] args) {

        System.out.println(shortestBeautifulSubstring("100011001", 3));
        System.out.println(shortestBeautifulSubstring("1011", 2));
        System.out.println(shortestBeautifulSubstring("000", 1));
    }

    public static String shortestBeautifulSubstring(String s, int k) {

        int N = s.length();

        int left = 0;

        int right = 0;

        int cnt = 0;

        String ans = null;

        while (right < N) {

            char c = s.charAt(right);

            if (c == '1') {
                cnt++;
            }

            while (cnt > k) {
                char c1 = s.charAt(left);
                if (c1 == '1')
                    cnt--;
                left++;
            }

            if (cnt == k) {

                while (s.charAt(left) == '0')
                    left++;

                if (ans == null || (right - left + 1) < ans.length() || ((right - left + 1) == ans.length() && s.substring(left, right + 1).compareTo(ans) < 0))
                    ans = s.substring(left, right + 1);
            }

            right++;
        }

        return ans == null ? "" : ans;
    }

}
