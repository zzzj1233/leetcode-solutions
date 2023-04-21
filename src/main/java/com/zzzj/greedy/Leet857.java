package com.zzzj.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-03-02 10:39
 */
public class Leet857 {

    public static void main(String[] args) {
        Leet857 solution = new Leet857();

        // 0,2,3
        System.out.println(solution.mincostToHireWorkers(new int[]{3, 1, 10, 10, 1}, new int[]{4, 8, 2, 2, 7}, 3));

        System.out.println(solution.mincostToHireWorkers(new int[]{10, 20, 5}, new int[]{70, 50, 30}, 2));
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {

        int N = quality.length;

        // 1. 按照rate排序

        int[][] combine = new int[N][2];

        for (int i = 0; i < N; i++) {
            combine[i][0] = quality[i];
            combine[i][1] = wage[i];
        }

        Arrays.sort(combine, Comparator.comparingDouble(this::rate));

        // 2. 维持一个最大堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);

        int sum = 0;

        for (int i = 0; i < k; i++) {
            int qt = combine[i][0];
            queue.add(qt);
            sum += qt;
        }

        double ans = rate(combine[k - 1]) * sum;

        for (int i = k; i < N; i++) {
            int qt = combine[i][0];

            if (qt < queue.peek()) {
                sum -= queue.remove();
                sum += qt;
                queue.add(qt);
            }

            double rate = rate(combine[i]);

            ans = Math.min(ans, rate * sum);
        }

        return ans;
    }

    public double rate(int[] item) {
        return ((double) item[1]) / item[0];
    }

}
