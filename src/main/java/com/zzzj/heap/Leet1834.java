package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-01-23 14:17
 */
public class Leet1834 {


    public static void main(String[] args) {
        System.out.println(Arrays.toString(getOrder(LeetUtils.convertInts("[[1,2],[2,4],[3,2],[4,1]]"))));
        System.out.println(Arrays.toString(getOrder(LeetUtils.convertInts("[[7,10],[7,12],[7,5],[7,4],[7,2]]"))));
    }

    public static int[] getOrder(int[][] tasks) {

        PriorityQueue<Integer> enqueueQueue = new PriorityQueue<>(Comparator.comparingInt(o -> tasks[o][0]));

        PriorityQueue<int[]> taskQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        for (int i = 0; i < tasks.length; i++) {
            enqueueQueue.add(i);
        }

        int time = 0;

        int[] ans = new int[tasks.length];

        int ansIndex = 0;

        while (!enqueueQueue.isEmpty()) {

            if (!taskQueue.isEmpty()) {
                int[] remove = taskQueue.remove();
                // 花费了这么多时间得加上
                time += remove[0];
                ans[ansIndex] = remove[1];
                ansIndex++;
            } else {
                if (time < tasks[enqueueQueue.peek()][0]){
                    time = tasks[enqueueQueue.peek()][0];
                }
            }

            while (!enqueueQueue.isEmpty() && time >= tasks[enqueueQueue.peek()][0]) {
                Integer index = enqueueQueue.remove();

                int[] task = tasks[index];

                int enqueueTime = task[0];

                int spend = task[1];

                taskQueue.add(new int[]{spend, index});
            }
        }

        while (!taskQueue.isEmpty()) {
            ans[ansIndex] = taskQueue.remove()[1];
            ansIndex++;
        }

        return ans;
    }

}
