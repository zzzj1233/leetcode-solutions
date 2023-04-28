package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-04-27 16:23
 */
public class Leet2462 {

    public static void main(String[] args) {

        System.out.println(totalCost(
                new int[]{1, 3, 0, 0, 3},
                2,
                2
        ));

//        System.exit(0);

        int N = 10000;

        for (int i = 0; i < N; i++) {

            int K = 500;

            int[] arr = ArrayUtil.generateArray(K, 0, 500);
            int k = LeetUtils.random.nextInt(K);
            int candidate = LeetUtils.random.nextInt(K) + 1;

            if (totalCost(arr, k, candidate) != right(arr, k, candidate)) {
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("k = " + k);
                System.out.println("candidate = " + candidate);
                System.out.println("totalCost(arr,k,candidate) = " + totalCost(arr, k, candidate));
                System.out.println("right(arr,k,candidate) = " + right(arr, k, candidate));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static long totalCost(int[] costs, int k, int candidates) {

        PriorityQueue<Integer> leftPQ = new PriorityQueue<>((o1, o2) -> {
            if (costs[o1] == costs[o2]) {
                return o1 - o2;
            }
            return costs[o1] - costs[o2];
        });

        PriorityQueue<Integer> rightPQ = new PriorityQueue<>((o1, o2) -> {
            if (costs[o1] == costs[o2]) {
                return o1 - o2;
            }
            return costs[o1] - costs[o2];
        });

        int N = costs.length;

        for (int i = 0; i < candidates; i++) {
            leftPQ.add(i);
        }

        int leftPQIndex = candidates;
        int rightPQIndex = Math.max(leftPQIndex, N - candidates);

        for (int i = rightPQIndex; i < N; i++) {
            rightPQ.add(i);
        }
        rightPQIndex--;

        long ans = 0;

        for (int i = 0; i < k; i++) {
            if (leftPQ.isEmpty()) ans += costs[rightPQ.remove()];
            else if (rightPQ.isEmpty()) ans += costs[leftPQ.remove()];
            else {
                Integer lp = leftPQ.peek();
                Integer rp = rightPQ.peek();

                int costL = costs[lp];
                int costR = costs[rp];

                if (costL == costR) {
                    if (lp < rp) {
                        leftPQ.remove();
                        ans += costL;
                    } else {
                        rightPQ.remove();
                        ans += costR;
                    }
                } else if (costL < costR) {
                    leftPQ.remove();
                    ans += costL;
                } else {
                    rightPQ.remove();
                    ans += costR;
                }
            }

            if (leftPQ.size() < candidates && leftPQIndex <= rightPQIndex) {
                leftPQ.add(leftPQIndex);
                leftPQIndex++;
            }

            if (rightPQ.size() < candidates && rightPQIndex >= leftPQIndex) {
                rightPQ.add(rightPQIndex);
                rightPQIndex--;
            }

        }

        return ans;
    }

    public static long right(int[] cost, int k, int candidates) {
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(), pq2 = new PriorityQueue<>();
        long ans = 0;
        int n = cost.length, i = 0, j = n - 1;
        int temp = candidates;
        while (i <= j && temp > 0) {
            pq1.offer(cost[i++]);
            temp--;
        }
        temp = candidates;
        while (i <= j && temp > 0) {
            pq2.offer(cost[j--]);
            temp--;
        }
        while (k > 0) {
            if (pq1.size() == 0) {
                ans += pq2.poll();
                if (i <= j) pq2.offer(cost[j--]);
            } else if (pq2.size() == 0) {
                ans += pq1.poll();
                if (i <= j) pq1.offer(cost[i++]);
            } else {
                if (pq1.peek() <= pq2.peek()) {
                    ans += pq1.poll();
                    if (i <= j) pq1.offer(cost[i++]);
                } else {
                    ans += pq2.poll();
                    if (i <= j) pq2.offer(cost[j--]);
                }
            }
            k--;
        }
        return ans;
    }

}
