package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2023-02-27 16:40
 */
public class Leet2552 {

    public static void main(String[] args) {
        System.out.println(minimumPartition("165462", 60));
    }

    public static int minimumPartition(String s, int k) {

        int N = s.length();

        int ans = 0;

        long sum = 0;

        for (int i = 0; i < N; i++) {

            int num = Character.digit(s.charAt(i), 10);

            if (num > k) {
                return -1;
            }

            long newSum = sum * 10 + num;

            if (newSum > k) {
                ans++;
                sum = num;
            } else {
                sum = newSum;
            }

        }

        return ans + 1;
    }

}
