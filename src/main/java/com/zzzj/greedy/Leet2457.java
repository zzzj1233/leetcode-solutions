package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2023-02-27 16:46
 */
public class Leet2457 {

    public static void main(String[] args) {
        System.out.println(makeIntegerBeautiful(467, 6));
        System.out.println(makeIntegerBeautiful(16, 6));
        System.out.println(makeIntegerBeautiful(8, 2));
        System.out.println(makeIntegerBeautiful(20, 2));
    }

    // 8
    // 2
    public static long makeIntegerBeautiful(long n, int target) {

        if (n <= target) {
            return 0;
        }

        String str = String.valueOf(n);

        int N = str.length();

        long[] preSum = new long[N + 1];

        long nSum = 0;

        for (int i = 1; i <= N; i++) {
            int num = Character.digit(str.charAt(i - 1), 10);
            preSum[i] = preSum[i - 1] + num;
            nSum += num;
        }

        if (nSum <= target) {
            return 0;
        }

        long ans = 0;

        for (int i = 0; i <= N; i++) {
            long curSum = preSum[i];
            if (curSum + 1 <= target) {
                long sum = 0;
                for (int j = i; j < N; j++) {
                    sum = sum * 10 + 9 - Character.digit(str.charAt(j), 10);
                }
                ans = sum + 1;
            }
        }

        return ans;
    }

}
