package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayCopyIterator;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-17 16:44
 */
public class Leet2552 {

    public static void main(String[] args) {

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int M = 50;

            int L = 1;
            int R = M;

            boolean[] used = new boolean[R + 1];

            int[] nums = new int[M];

            int index = 0;

            while (index < M) {

                int num = LeetUtils.random.nextInt(R) + L;

                if (used[num]) continue;

                used[num] = true;

                nums[index] = num;

                index++;
            }

            ArrayCopyIterator it = new ArrayCopyIterator(nums);

            long r1 = countQuadruplets(it.next());

            long r2 = right(it.next());

            if (r1 != r2) {
                System.out.println("Error");
                System.out.println("it.next() = " + Arrays.toString(it.next()));
                System.out.println("r1 = " + r1);
                System.out.println("r2 = " + r2);
                return;
            }

        }

        System.out.println("Ok");

    }

    public static long countQuadruplets(int[] nums) {

        int N = nums.length;

        if (N < 4)
            return 0;

        int[][] max = new int[N][];

        MinBinaryIndexedTree minTree = new MinBinaryIndexedTree(N);

        minTree.update(nums[0]);

        max[N - 1] = new int[N + 1];

        for (int i = 0; i <= nums[N - 1]; i++) {
            max[N - 1][i] = 1;
        }

        long ans = 0;

        for (int N2 = 1; N2 < N - 2; N2++) {

            for (int N3 = N - 2; N3 > N2; N3--) {

                if (max[N3] == null) {

                    max[N3] = new int[N + 1];

                    System.arraycopy(max[N3 + 1], 0, max[N3], 0, max[N3].length);

                    for (int i = 0; i <= nums[N3]; i++) {
                        max[N3][i]++;
                    }

                }

                if (nums[N3] < nums[N2]) {

                    // 右边有多少
                    int rightCnt = max[N3][nums[N2]];

                    // 左边有多少
                    int leftCnt = minTree.query(nums[N3]);

                    long cnt = (long) rightCnt * leftCnt;

                    ans += cnt;
                }

            }

            minTree.update(nums[N2]);
        }

        return ans;
    }

    private static class MinBinaryIndexedTree {

        private final int[] tree;

        private MinBinaryIndexedTree(int N) {
            this.tree = new int[N + 2];
        }

        public void update(int x) {
            int index = x + 1;
            while (index < tree.length) {
                tree[index]++;
                index += lowbit(index);
            }
        }

        public int query(int x) {
            int index = x + 1;

            int r = 0;

            while (index > 0) {
                r += tree[index];
                index -= lowbit(index);
            }

            return r;
        }

        private int lowbit(int x) {
            return x & (-x);
        }
    }

    public static long right(int[] nums) {
        int n = nums.length;
        //pre[i][j]是index < i 且 num[index] < j 的数目
        int[][] pre = new int[n + 10][n + 10];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i].clone();
            for (int j = nums[i] + 1; j <= n; j++) {
                pre[i + 1][j] += 1;
            }
        }

        //suf[i][j]是index > i 且 num[index] > j 的数目
        int[][] suf = new int[n + 10][n + 10];
        for (int i = n - 1; i > 0; i--) {
            suf[i - 1] = suf[i].clone();
            for (int j = nums[i] - 1; j >= 1; j--) {
                suf[i - 1][j] += 1;
            }
        }
        long ans = 0;
        for (int j = 1; j < n - 2; j++) {
            for (int k = j + 1; k < n - 1; k++) {
                if (nums[k] < nums[j]) {
                    ans += pre[j][nums[k]] * suf[k][nums[j]];
                }
            }
        }
        return ans;
    }

}