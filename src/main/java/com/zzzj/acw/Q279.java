package com.zzzj.acw;

import java.util.Scanner;

public class Q279 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        long[] f = new long[N + 1];

        final long mod = 2147483648L;

        f[0] = 1;

        for (int i = 1; i <= N; i++) {

            for (int j = i; j <= N; j++) {
                f[j] = (f[j] + f[j - i]) % mod;
            }

        }

        System.out.println((f[N] - 1) % mod);
    }

}
