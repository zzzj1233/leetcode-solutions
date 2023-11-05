package com.zzzj.greedy;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-10-20 11:05
 */
public class Leet2398 {

    public static void main(String[] args) {

        System.out.println(maximumRobots(new int[]{3, 6, 1, 3, 4}, new int[]{2, 1, 3, 4, 5}, 25));

        System.out.println(maximumRobots(new int[]{11, 12, 19}, new int[]{10, 8, 7}, 19));
//
        System.out.println(maximumRobots(new int[]{8, 76, 74, 9, 75, 71, 71, 42, 15, 58, 88, 38, 56, 59, 10, 11}, new int[]{1, 92, 41, 63, 22, 37, 37, 8, 68, 97, 39, 59, 45, 50, 29, 37}, 412));

    }

    public static int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {

        int N = chargeTimes.length;

        int left = 0;

        int right = 0;

        int ans = 0;

        long totalCost = 0;

        LinkedList<Integer> stack = new LinkedList<>();

        while (right < N) {

            totalCost += runningCosts[right];

            int charge = chargeTimes[right];

            while (!stack.isEmpty() && chargeTimes[stack.peekFirst()] <= charge)
                stack.removeFirst();

            stack.addFirst(right);

            while (!stack.isEmpty() && totalCost * (right - left + 1) + chargeTimes[stack.peekLast()] > budget) {
                if (left == stack.peekLast())
                    stack.removeLast();
                totalCost -= runningCosts[left];
                left++;
            }

            ans = Math.max(ans, right - left + 1);

            right++;
        }

        return ans;
    }

}
