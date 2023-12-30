package com.zzzj.acw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Q12 {

    private static void printValues(int[][] w, int... indexes) {
        int value = 0;
        int weight = 0;
        for (int index : indexes) {
            value += w[index - 1][1];
            weight += w[index - 1][0];
        }
        System.out.printf("weight : %d , value : %d %n", weight, value);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner("30 300\n" +
                "59 113\n" +
                "92 144\n" +
                "95 74\n" +
                "6 3\n" +
                "44 89\n" +
                "8 11\n" +
                "77 214\n" +
                "60 169\n" +
                "37 110\n" +
                "49 127\n" +
                "21 36\n" +
                "57 80\n" +
                "84 49\n" +
                "16 33\n" +
                "27 69\n" +
                "57 78\n" +
                "70 193\n" +
                "47 131\n" +
                "16 11\n" +
                "44 75\n" +
                "44 39\n" +
                "93 89\n" +
                "74 190\n" +
                "34 100\n" +
                "78 207\n" +
                "2 5\n" +
                "74 181\n" +
                "25 38\n" +
                "46 104\n" +
                "55 85");

        int N = scanner.nextInt();

        int V = scanner.nextInt();

        int[][] w = new int[N][2];

        for (int i = 0; i < N; i++) {
            w[i][0] = scanner.nextInt();
            w[i][1] = scanner.nextInt();
        }

        // printValues(w, 7, 9, 15, 18, 24, 25);
        // printValues(w, 8, 9, 10, 17, 18, 24, 26);

        int[][] dp = new int[N + 1][V + 1];

        for (int i = N - 1; i >= 0; i--) {

            int weight = w[i][0];

            int value = w[i][1];

            for (int j = V; j >= 0; j--) {
                dp[i][j] = Math.max(
                        dp[i + 1][j],
                        j - weight >= 0 ? dp[i + 1][j - weight] + value : Integer.MIN_VALUE
                );
            }

        }

        // System.out.println(dp[0][V]);

        int X = V;

        List<Integer> path = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            int weight = w[i][0];

            int value = w[i][1];

            if (X - weight < 0)
                continue;

            if (dp[i + 1][X - weight] + value == dp[i][X]) {
                X -= weight;
                path.add(i + 1);
            }

        }

        System.out.println(path.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }

}
