package com.zzzj.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-02-28 11:43
 */
public class Leet2542 {

    public static void main(String[] args) {
        System.out.println(maxScore(
                new int[]{1, 3, 3, 2},
                new int[]{2, 1, 3, 4},
                3
        ));
    }

    public static long maxScore(int[] nums1, int[] nums2, int k) {

        int N = nums1.length;

        int[][] combine = new int[N][2];

        for (int i = 0; i < N; i++) {
            combine[i][0] = nums1[i];
            combine[i][1] = nums2[i];
        }

        Arrays.sort(combine, (o1, o2) -> o2[1] - o1[1]);

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);

        long ans = 0;

        long sum = 0;

        for (int i = 0; i < k; i++) {

            int[] item = combine[i];

            int num1 = item[0];

            int num2 = item[1];

            queue.add(num1);

            sum += num1;

            ans = sum * num2;
        }

        for (int i = k; i < N; i++) {

            int[] item = combine[i];

            int num1 = item[0];

            int num2 = item[1];

            int min = queue.peek();

            if (num1 <= min) {
                continue;
            }

            long sub = num1 - min;

            sum += sub;

            queue.remove();
            queue.add(num1);

            ans = Math.max(ans, sum * num2);
        }

        return ans;
    }

}
