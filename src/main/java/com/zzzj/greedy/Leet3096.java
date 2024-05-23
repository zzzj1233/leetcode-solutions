package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2024-04-01 11:37
 */
public class Leet3096 {

    public static void main(String[] args) {

        System.out.println(minimumLevels(new int[]{1, 0, 1, 0}));

        System.out.println(minimumLevels(new int[]{1, 1, 1, 1, 1}));

        System.out.println(minimumLevels(new int[]{0, 0}));

    }

    public static int minimumLevels(int[] possible) {

        int N = possible.length;

        long[] ps = new long[N + 1];

        for (int i = 1; i <= N; i++)
            ps[i] = ps[i - 1] + (possible[i - 1] == 0 ? -1 : 1);

        for (int i = 0; i < N; i++) {
            // 包括i
            if (ps[i + 1] > ps[N] - ps[i + 1])
                return i + 1;
        }

        return -1;
    }

}
