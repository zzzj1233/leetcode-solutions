package com.zzzj.leet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-05-07 18:52
 */
public class Leet265 {

    // [[20,19,11,13,12,16,16,17,15,9,5,18],[3,8,15,17,19,8,18,3,11,6,7,12],[15,4,11,1,18,2,10,9,3,6,4,15]]
    public static void main(String[] args) {
        System.out.println(minCostII(LeetUtils.convertInts("[[15,17,15,20,7,16,6,10,4,20,7,3,4],[11,3,9,13,7,12,6,7,5,1,7,18,9]]")));
    }

    public static int minCostII(int[][] costs) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(costs.length, Comparator.comparingInt(value -> value[0]));


        for (int i = 0; i < costs[0].length; i++) {
            queue.add(new int[]{costs[0][i], i});
        }

        PriorityQueue<int[]> queue2 = queue;

        for (int i = 1; i < costs.length; i++) {

            queue2 = new PriorityQueue<>(costs.length, Comparator.comparingInt(value -> value[0]));

            for (int j = 0; j < costs[i].length; j++) {
                int[] first = queue.peek();
                int cost;
                if (first[1] == j) {
                    queue.remove();
                    int[] second = queue.peek();
                    cost = second[0];
                    queue.add(first);
                } else {
                    cost = first[0];
                }
                queue2.add(new int[]{cost + costs[i][j], j});
            }

            queue = queue2;
        }

        return queue.remove()[0];
    }

}
