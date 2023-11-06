package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-11-06 15:03
 */
public class Leet1649 {

    public static void main(String[] args) {

        System.out.println(createSortedArray(new int[]{1, 3, 3, 3, 2, 4, 2, 1, 2}));

        System.out.println(createSortedArray(new int[]{1, 2, 3, 6, 5, 4}));

        System.out.println(createSortedArray(new int[]{1, 5, 6, 2}));

    }

    static final int MOD = 1000000007;

    public static int createSortedArray(int[] instructions) {

        int N = instructions.length;

        int max = Arrays.stream(instructions).max().orElse(0);

        BIT tree = new BIT(max + 1);

        int ans = 0;

        for (int i = 0; i < N; i++) {

            ans = (ans + Math.min(
                    tree.query(max) - tree.query(instructions[i]),
                    tree.query(instructions[i] - 1)
            )) % MOD;

            tree.update(instructions[i]);
        }

        return ans;
    }

    private static class BIT {

        private final long[] data;

        private BIT(int N) {
            this.data = new long[N + 1];
        }

        private void update(int x) {
            int index = x + 1;
            while (index < data.length) {
                data[index]++;
                index += lowbit(index);
            }
        }

        private int query(int x) {
            int index = x + 1;
            long cnt = 0;
            while (index > 0) {
                cnt = (cnt + data[index]) % MOD;
                index -= lowbit(index);
            }
            return (int) (cnt % MOD);
        }

        private int lowbit(int x) {
            return x & -x;
        }

    }

}
