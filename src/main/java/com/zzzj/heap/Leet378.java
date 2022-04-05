package com.zzzj.heap;

import java.util.PriorityQueue;

/**
 * @author Zzzj
 * @create 2022-03-13 09:42
 */
public class Leet378 {

    public static void main(String[] args) {
        System.out.println(kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 4));
    }

    public static int checkRange(int mid, int row, int k) {
        int right = (mid + 1) * row - 1;
        int left = mid * row;
        int truly = k - 1;
        if (truly > right) {
            return 1;
        } else if (truly < left) {
            return -1;
        } else {
            return 0;
        }
    }

    // 使用二分查找
    public static int kthSmallest(int[][] matrix, int k) {
        // 第K大的元素
        int l = 0;
        int r = matrix.length;
        int row = r;

        int mid = (r + l) / 2;

        // range = (mid * row) ~ + (mid + 1) * row - 1
        int range;

        while ((range = checkRange(mid, row, k)) != 0) {
            if (range == 1) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
            mid = (r + l) / 2;
        }

        int truly = (k - 1) - (mid * row);

        return matrix[mid][truly];
    }

    // 使用优先队列
    public static int queue(int[][] matrix, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int[] ints : matrix) {

            for (int i : ints) {
                queue.add(i);
            }

        }

        for (int i = 0; i < k - 1; i++) {
            queue.remove();
        }

        return queue.peek();
    }


}
