package com.zzzj.bit;

import com.zzzj.leet.LeetUtils;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-05-17 17:49
 */
public class Leet1738 {

    public static void main(String[] args) {

        System.out.println(kthLargestValue(LeetUtils.convertInts("[[5,2],[1,6]]"), 1));

        System.out.println(kthLargestValue(LeetUtils.convertInts("[[5,2],[1,6]]"), 2));

        System.out.println(kthLargestValue(LeetUtils.convertInts("[[5,2],[1,6]]"), 3));

        System.out.println(kthLargestValue(LeetUtils.convertInts("[[5,2],[1,6]]"), 4));

    }

    public static int kthLargestValue(int[][] matrix, int k) {

        int M = matrix.length;

        int N = matrix[0].length;

        int[][] preSum = new int[M + 1][N + 1];

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);

        for (int i = 1; i <= M; i++) {

            for (int j = 1; j <= N; j++) {

                preSum[i][j] = preSum[i - 1][j] ^ preSum[i][j - 1] ^ preSum[i - 1][j - 1] ^ matrix[i - 1][j - 1];

                if (queue.size() < k) {
                    queue.add(preSum[i][j]);
                } else if (queue.peek() < preSum[i][j]) {
                    queue.remove();
                    queue.add(preSum[i][j]);
                }
            }

        }

        return queue.peek();
    }

}
