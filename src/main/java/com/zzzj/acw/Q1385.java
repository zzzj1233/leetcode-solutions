package com.zzzj.acw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q1385 {

    static Map<Integer, Integer> idMap;

    static int[] id;

    static int max;

    static int K = 6;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 折扣数量
        id = new int[K];
        idMap = new HashMap<>(8);
        max = 0;

        int M = scanner.nextInt();

        int[][] W = new int[M][];

        for (int i = 0; i < M; i++) {
            // N个商品
            int n = scanner.nextInt();

            int[] w = new int[K];

            for (int j = 0; j < n; j++) {

                int goodsNum = scanner.nextInt();

                int goodsCnt = scanner.nextInt();

                w[id(goodsNum)] = goodsCnt;
            }

            int totalPrice = scanner.nextInt();

            w[K - 1] = totalPrice;

            W[i] = w;
        }

        int N = scanner.nextInt();

        int[] V = new int[K];

        int[] P = new int[K];

        for (int i = 0; i < N; i++) {

            int goodsNum = scanner.nextInt();

            int goodsCnt = scanner.nextInt();

            int price = scanner.nextInt();

            int ID = id(goodsNum);

            P[ID] = price;

            V[ID] = goodsCnt;
        }

        int[][][][][] f = new int[K][K][K][K][K];

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                for (int k = 0; k < K; k++) {
                    for (int x = 0; x < K; x++) {
                        for (int y = 0; y < K; y++) {
                            f[i][j][k][x][y] = P[0] * i + P[1] * j + P[2] * k + P[3] * x + P[4] * y;
                        }
                    }
                }
            }
        }


        for (int i = 0; i < M; i++) {
            update(f, W[i]);
        }

        System.out.println(f[V[0]][V[1]][V[2]][V[3]][V[4]]);
    }


    private static int id(int goodsNum) {
        if (idMap.containsKey(goodsNum))
            return idMap.get(goodsNum);
        idMap.put(goodsNum, max);
        id[max] = goodsNum;
        return max++;
    }

    public static void update(
            int[][][][][] f,
            int[] v
    ) {
        for (int i = 0; i < K; i++) {
            if (v[0] > i)
                continue;
            for (int j = 0; j < K; j++) {
                if (v[1] > j)
                    continue;
                for (int k = 0; k < K; k++) {
                    if (v[2] > k)
                        continue;
                    for (int x = 0; x < K; x++) {
                        if (v[3] > x)
                            continue;
                        for (int y = 0; y < K; y++) {
                            if (v[4] > y)
                                continue;
                            f[i][j][k][x][y] = Math.min(
                                    f[i][j][k][x][y],
                                    f[i - v[0]][j - v[1]][k - v[2]][x - v[3]][y - v[4]] + v[5]
                            );
                        }
                    }
                }
            }
        }
    }

}
