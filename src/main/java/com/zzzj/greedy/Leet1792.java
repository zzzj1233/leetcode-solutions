package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-11-03 14:54
 */
public class Leet1792 {

    public static void main(String[] args) {
        System.out.println(maxAverageRatio(LeetUtils.convertInts("[[1,2],[3,5],[2,2]]"), 2));
    }

    public static double maxAverageRatio(int[][] classes, int extraStudents) {

        int N = classes.length;

        PriorityQueue<ExamClass> queue = new PriorityQueue<>(N, (o1, o2) -> {
            Double r10 = o1.passRate(0);
            Double r11 = o1.passRate(1);

            Double r20 = o2.passRate(0);
            Double r21 = o2.passRate(1);

            return Double.valueOf((r21 - r20)).compareTo((r11 - r10));
        });

        for (int[] ints : classes) {
            queue.add(new ExamClass(ints[0], ints[1]));
        }

        while (extraStudents > 0 && !queue.isEmpty()) {
            ExamClass max = queue.remove();

            max.pass++;
            max.total++;

            queue.add(max);

            extraStudents--;
        }

        double ans = 0;

        while (!queue.isEmpty()) {
            ans += queue.remove().passRate(0);
        }

        return ans / N;
    }

    private static class ExamClass {
        int pass;
        int total;

        public Double passRate(int extra) {
            return ((double) pass + extra) / (total + extra);
        }

        public ExamClass(int pass, int total) {
            this.pass = pass;
            this.total = total;
        }
    }

}
