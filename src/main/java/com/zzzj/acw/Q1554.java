package com.zzzj.acw;

import java.util.*;

public class Q1554 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner("100 49\n" +
                "30 26 29 5 26 27 2 41 27 35 26 5 2 8 7 41 27 35 41 27 30 30 26 26 41 27 35 41 30 30 8 30 8 26 30 7 41 29 8 5 29 27 2 8 26 30 30 30 27 2 2 35 41 2 26 29 30 41 29 2 5 29 8 30 41 7 8 30 41 2 27 2 7 35 2 2 27 30 5 26 2 41 5 27 5 30 35 8 26 29 35 8 2 7 7 7 7 35 26 8");

        int N = scanner.nextInt();

        int V = scanner.nextInt();

        boolean[][] f = new boolean[N + 1][V + 1];

        Integer[] W = new Integer[N];

        for (int i = 0; i < N; i++)
            W[i] = scanner.nextInt();

        Arrays.sort(W, (o1, o2) -> o2 - o1);

        f[0][0] = true;

        for (int i = 1; i <= N; i++) {

            int w = W[i - 1];

            for (int v = 0; v <= V; v++) {

                f[i][v] |= f[i - 1][v] || (v >= w && f[i - 1][v - w]);

            }

        }

        if (!f[N][V]) {
            System.out.println("No Solution");
            return;
        }

        int v = V;


        for (int i = N; i >= 1 && v > 0; i--) {

            Integer w = W[i - 1];

            if (f[i][v] && f[i - 1][v - w]) {
                v -= w;
                System.out.print(w + " ");
            }

        }

    }

}
