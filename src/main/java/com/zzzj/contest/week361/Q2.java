package com.zzzj.contest.week361;


public class Q2 {

    public static void main(String[] args) {

        System.out.println(minimumOperations("2245047"));

        System.out.println(minimumOperations("2908305"));

        System.out.println(minimumOperations("10"));

        System.out.println(minimumOperations("2713539"));

        System.out.println(minimumOperations("1"));

        System.out.println(minimumOperations("820366"));

    }

    public static int minimumOperations(String num) {

        int N = num.length();

        if (N <= 2) {
            if (isCandidate(num, 0, N)) return 0;
            return num.contains("0") ? N - 1 : N;
        }

        int ans = Integer.MAX_VALUE;

        for (String candidate : candidates) {
            ans = Math.min(ans, find(num, candidate.charAt(0), candidate.charAt(1)));
        }

        if (ans == Integer.MAX_VALUE) {
            return num.contains("0") ? N - 1 : N;
        }

        return ans;
    }

    public static int find(String num, char c1, char c2) {
        int N = num.length();

        boolean findC2 = false;

        boolean findC1 = false;

        int cnt = 0;

        for (int i = N - 1; i >= 0; i--) {

            if (!findC2 && num.charAt(i) == c2) {
                findC2 = true;
            } else if (findC2 && num.charAt(i) == c1) {
                findC1 = true;
            } else {
                cnt++;
            }

            if (findC1)
                break;

        }

        return findC1 ? cnt : Integer.MAX_VALUE;
    }

    private static final String[] candidates = {
            "00",
            "25",
            "50",
            "75"
    };

    public static boolean isCandidate(String str, int left, int right) {
        String substring = str.substring(left, right);

        for (String candidate : candidates) {
            if (candidate.equals(substring))
                return true;
        }
        return false;
    }

}
