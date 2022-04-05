package com.zzzj.heap;

import java.util.PriorityQueue;

/**
 * @author Zzzj
 * @create 2022-03-17 11:48
 */
public class Leet1753 {

    public static void main(String[] args) {
        System.out.println(maximumScore(2, 4, 6));
        System.out.println(maximumScore(4, 4, 6));
        System.out.println(maximumScore(1, 8, 8));
    }

    public static int maximumScore(int a, int b, int c) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(3, (o1, o2) -> o2 - o1);

        queue.add(a);
        queue.add(b);
        queue.add(c);

        int score = 0;

        while (queue.size() >= 2) {
            Integer max1 = queue.remove();
            Integer max2 = queue.remove();

            if (max1 > 1) {
                queue.add(max1 - 1);
            }

            if (max2 > 1) {
                queue.add(max2 - 1);
            }

            score++;
        }

        return score;
    }

}
