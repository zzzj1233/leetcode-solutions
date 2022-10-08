package com.zzzj.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-10-08 22:21
 */
public class Leet1882 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(assignTasks(new int[]{3, 3, 2}, new int[]{1, 2, 3, 2, 1, 2})));
    }

    public static int[] assignTasks(int[] servers, int[] tasks) {

        PriorityQueue<RunningComputer> pq1 = new PriorityQueue<>((o1, o2) -> {
            return o1.weight == o2.weight ? o1.index - o2.index : o1.weight - o2.weight;
        });

        PriorityQueue<RunningComputer> pq2 = new PriorityQueue<>((o1, o2) -> {
            if (o1.done == o2.done) {
                return o1.weight == o2.weight ? o1.index - o2.index : o1.weight - o2.weight;
            }
            return o1.done - o2.done;
        });

        for (int i = 0; i < servers.length; i++) {
            pq1.add(new RunningComputer(servers[i], i));
        }

        int N = tasks.length;

        int[] ans = new int[N];

        int time = 0;

        for (int i = 0; i < N; i++, time++) {
            while (!pq2.isEmpty() && i >= pq2.peek().done) {
                pq1.add(pq2.remove());
            }

            // 没有空闲电脑了
            if (pq1.isEmpty()) {
                RunningComputer latestFinish = pq2.remove();
                time = latestFinish.done;
                pq1.add(latestFinish);
            }

            RunningComputer idle = pq1.remove();
            ans[i] = idle.index;
            idle.done = time + tasks[i];
            pq2.add(idle);
        }

        return ans;
    }

    private static class RunningComputer {
        int weight;
        int index;
        int done;

        public RunningComputer(int weight, int index) {
            this.weight = weight;
            this.index = index;
        }
    }

}
