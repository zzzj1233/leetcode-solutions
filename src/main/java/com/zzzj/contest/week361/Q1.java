package com.zzzj.contest.week361;

public class Q1 {

    public static void main(String[] args) {

        System.out.println(countSymmetricIntegers(1, 100));

        System.out.println(countSymmetricIntegers(1200, 1230));

    }

    public static int countSymmetricIntegers(int low, int high) {

        int ans = 0;

        for (int i = low; i <= high; i++) {

            String s = String.valueOf(i);

            if (s.length() % 2 != 0)
                continue;

            int mid = s.length() / 2;

            int left = 0;

            for (int j = 0; j < mid; j++) {
                left += Character.digit(s.charAt(j), 10);
            }

            int right = 0;

            for (int j = mid; j < s.length(); j++) {
                right += Character.digit(s.charAt(j), 10);
            }

            if (left == right)
                ans++;
        }

        return ans;
    }

}
