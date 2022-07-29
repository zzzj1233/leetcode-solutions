package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2021-12-15 17:49
 */
public class Leet632 {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(smallestRange(Arrays.asList(Arrays.asList(4, 10, 15, 24, 26), Arrays.asList(0, 9, 12, 20), Arrays.asList(5, 18, 22, 30)))));
//
//        System.out.println(Arrays.toString(smallestRange(LeetUtils.convertLists("[[1,2,3],[1,2,3],[1,2,3]]"))));

        for (int i = 0; i < 10000; i++) {
            int N = LeetUtils.random.nextInt(10) + 1;
            int M = LeetUtils.random.nextInt(10) + 2;
            List<List<Integer>> lists = new ArrayList<>(N);
            for (int j = 0; j < N; j++) {
                List<Integer> subList = new ArrayList<>(M);
                for (int k = 0; k < M; k++) {
                    subList.add(LeetUtils.random.nextInt(30) - LeetUtils.random.nextInt(30));
                }
                subList.sort(Comparator.comparingInt(o -> o));
                lists.add(subList);
            }
            if (!Arrays.equals(smallestRange(lists), right(lists))) {
                System.out.println("Error");
                System.out.println(lists);
                return;
            }
        }
        System.out.println("Ok");
    }

    // 贪心解法
    public static int[] smallestRange(List<List<Integer>> nums) {
        int N = nums.size();

        int M = nums.get(0).size();

        PriorityQueue<int[]> minQueue = new PriorityQueue<>(N * M, Comparator.comparingInt(o -> nums.get(o[0]).get(o[1])));

        PriorityQueue<int[]> maxQueue = new PriorityQueue<>(N * M, Comparator.comparingInt(o -> nums.get(((int[]) o)[0]).get(((int[]) o)[1])).reversed());

        for (int i = 0; i < N; i++) {
            int[] indexes = {i, 0};

            minQueue.add(indexes);
            maxQueue.add(indexes);
        }

        int[] min = minQueue.peek();

        int[] max = maxQueue.peek();

        int ansMin = nums.get(min[0]).get(min[1]);

        int ansMax = nums.get(max[0]).get(max[1]);

        if (ansMin == ansMax) {
            return new int[]{ansMin, ansMax};
        }

        // 尝试把min扩大
        int index1 = min[0];
        int index2 = min[1];

        while (index2 + 1 < nums.get(index1).size()) {
            minQueue.remove();
            int[] indexes = {index1, index2 + 1};
            minQueue.add(indexes);
            maxQueue.add(indexes);

            min = minQueue.peek();
            max = maxQueue.peek();

            int newAnsMin = nums.get(min[0]).get(min[1]);
            int newAnsMax = nums.get(max[0]).get(max[1]);

            if (newAnsMax - newAnsMin < ansMax - ansMin) {
                ansMax = newAnsMax;
                ansMin = newAnsMin;
            }
            index1 = min[0];
            index2 = min[1];
        }

        return new int[]{ansMin, ansMax};
    }

    public static int[] right(List<List<Integer>> nums) {
        int rangeLeft = 0, rangeRight = Integer.MAX_VALUE;
        int minRange = rangeRight - rangeLeft;
        int max = Integer.MIN_VALUE;
        int size = nums.size();
        int[] next = new int[size];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer index1, Integer index2) {
                return nums.get(index1).get(next[index1]) - nums.get(index2).get(next[index2]);
            }
        });
        for (int i = 0; i < size; i++) {
            priorityQueue.offer(i);
            max = Math.max(max, nums.get(i).get(0));
        }
        while (true) {
            int minIndex = priorityQueue.poll();
            int curRange = max - nums.get(minIndex).get(next[minIndex]);
            if (curRange < minRange) {
                minRange = curRange;
                rangeLeft = nums.get(minIndex).get(next[minIndex]);
                rangeRight = max;
            }
            next[minIndex]++;
            if (next[minIndex] == nums.get(minIndex).size()) {
                break;
            }
            priorityQueue.offer(minIndex);
            max = Math.max(max, nums.get(minIndex).get(next[minIndex]));
        }
        return new int[]{rangeLeft, rangeRight};
    }
}
