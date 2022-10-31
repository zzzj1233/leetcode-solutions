package com.zzzj.leet;

public class Leet2177 {

    public static long[] sumOfThree(long num) {

        // 假设 (N - 1) + (N) + (N + 1) == num

        // 那么 3N = num

        if (num % 3 == 0) {
            long pivot = num / 3;
            return new long[]{pivot - 1, pivot, pivot + 1};
        }

        return new long[0];
    }

}
