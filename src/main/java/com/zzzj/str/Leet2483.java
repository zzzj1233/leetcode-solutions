package com.zzzj.str;

/**
 * @author zzzj
 * @create 2023-08-07 16:54
 */
public class Leet2483 {

    public static void main(String[] args) {

        System.out.println(bestClosingTime("YYNY"));

        System.out.println(bestClosingTime("NNNNN"));

        System.out.println(bestClosingTime("YYYY"));

    }

    public static int bestClosingTime(String customers) {

        int N = customers.length();

        int[] prefix = new int[N + 1];

        int[] suffix = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1];
            if (customers.charAt(i - 1) == 'N') {
                prefix[i]++;
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1];
            if (customers.charAt(i) == 'Y') {
                suffix[i]++;
            }
        }

        int min = Integer.MAX_VALUE;

        int ans = -1;

        for (int i = 0; i <= N; i++) {

            int cost = prefix[i] + suffix[i];

            if (cost < min) {
                min = cost;
                ans = i;
            }
        }

        return ans;
    }

}
