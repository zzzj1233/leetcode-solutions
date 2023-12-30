package com.zzzj.acw;

import java.util.Scanner;

public class Q1049 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        for (int C = 0; C < T; C++) {

            int N = scanner.nextInt();

            int[] stat = new int[2];

            for (int i = 0; i < N; i++) {

                int value = scanner.nextInt();

                int y = value + stat[0];
                int n = Math.max(stat[1], stat[0]);

                stat[0] = n;
                stat[1] = y;
            }

            System.out.println(Math.max(stat[0], stat[1]));
        }

    }

}
