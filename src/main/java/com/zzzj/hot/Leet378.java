package com.zzzj.hot;

import com.zzzj.leet.LeetUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-05-06 11:57
 */
public class Leet378 {

    public static void main(String[] args) {
        System.out.println(kthSmallest(LeetUtils.convertInts("[[1,5,9],[10,11,13],[12,13,15]]"), 6));
    }

    // 1  5  9
    // 10 11 13
    // 12 13 15
    public static int kthSmallest(int[][] matrix, int k) {
        int N = matrix.length;
        int M = matrix[0].length;

        boolean[][] visited = new boolean[N][M];

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> matrix[o[0]][o[1]]));

        queue.add(new int[]{0, 0});

        visited[0][0] = true;

        for (int i = 0; i < k - 1; i++) {
            if (queue.isEmpty()) {
                return -1;
            }
            int[] item = queue.remove();
            int r = item[0];
            int c = item[1];
            if (r + 1 < matrix.length && !visited[r + 1][c]) {
                visited[r + 1][c] = true;
                queue.add(new int[]{r + 1, c});
            }
            if (c + 1 < matrix[r].length && !visited[r][c + 1]) {
                visited[r][c + 1] = true;
                queue.add(new int[]{r, c + 1});
            }
        }

        if (queue.isEmpty()) {
            return -1;
        }

        int[] last = queue.remove();

        return matrix[last[0]][last[1]];
    }

}
