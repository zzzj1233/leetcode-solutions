package com.zzzj.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-03-11 16:16
 */
public class Leet1337 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(kWeakestRows(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        }, 3)));
    }

    public static int[] kWeakestRows(int[][] mat, int k) {
        int[] ans = new int[k];

        PriorityQueue<int[]> queue = new PriorityQueue<>(mat.length, (o1, o2) -> {
            return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
        });

        for (int i = 0; i < mat.length; i++) {

            final int[] row = mat[i];

            // 2分法找到军人的个数
            int l = 0;
            int r = row.length;
            int mid = (r + l) / 2;

            while (l <= r && mid < row.length && (row[mid] != 0 || (mid > 0 && row[mid - 1] != 1))) {
                // 往左找
                if (row[mid] == 0) {
                    r = mid - 1;
                    // 往右找
                } else {
                    l = mid + 1;
                }
                mid = (r + l) / 2;
            }

            int soldier = mid;

            queue.add(new int[]{soldier, i});
        }

        for (int i = 0; i < k; i++) {
            ans[i] = queue.remove()[1];
        }

        return ans;
    }

}
