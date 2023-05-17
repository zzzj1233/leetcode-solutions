package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-05-15 17:48
 */
public class Leet2679 {

    public static void main(String[] args) {

        System.out.println(matrixSum(
                LeetUtils.convertInts("[[7,2,1],[6,4,2],[6,5,3],[3,2,1]]")
        ));

    }

    public static int matrixSum(int[][] nums) {

        int M = nums.length;

        PriorityQueue[] queues = new PriorityQueue[M];

        for (int i = 0; i < M; i++) {

            int N = nums[i].length;

            queues[i] = new PriorityQueue<Integer>(N, (o1, o2) -> o2 - o1);

            for (int j = 0; j < N; j++) {
                queues[i].add(nums[i][j]);
            }

        }

        int ans = 0;

        boolean anyResult = true;

        while (anyResult) {

            int max = 0;

            anyResult = false;

            for (int i = 0; i < M; i++) {

                PriorityQueue<Integer> queue = queues[i];

                if (!queue.isEmpty()) {
                    max = Math.max(max, queue.remove());
                    anyResult = true;
                }

            }

            ans += max;
        }


        return ans;
    }

}
