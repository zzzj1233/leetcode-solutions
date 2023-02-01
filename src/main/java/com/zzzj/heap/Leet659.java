package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayCopyIterator;

import java.util.*;

/**
 * @author zzzj
 * @create 2022-04-01 15:02
 */
public class Leet659 {

    public static void main(String[] args) {

        // 1 2 3 4 5 6 6 7
        // 1 2 3
        // 4 5 6 7

        // 1 2 3 4 5
        // 6 6 7
        System.out.println(isPossible(new int[]{1, 1, 1, 3, 3, 3, 4, 5, 8}));

        System.exit(0);

        int N = 1000000;

        for (; ; ) {

            int M = LeetUtils.random.nextInt(10);

            int[] arr = new int[M];

            for (int j = 0; j < M; j++) {
                arr[j] = LeetUtils.random.nextInt(M);
            }

            ArrayCopyIterator iterator = new ArrayCopyIterator(arr, true);

            if (isPossible(iterator.next()) != right(iterator.next())) {
                System.out.println("Error");
                System.out.println(Arrays.toString(iterator.next()));
                System.out.println(isPossible(iterator.next()));
                System.out.println(right(iterator.next()));
                break;
            }

        }

        System.out.println("Ok");
    }

    public static boolean isPossible(int[] nums) {

        int N = nums.length;

        // 以key为结尾的次数
        Map<Integer, Integer> endMap = new HashMap<>();

        Map<Integer, Integer> countMap = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }

        for (int i = 0; i < N; i++) {
            int num = nums[i];

            if (countMap.get(num) == 0) {
                continue;
            }

            int next = num + 1;

            int nextCount = 0;

        }

        for (int i = 0; i < N; i++) {
            if (countMap.get(nums[i]) == 0) {
                continue;
            }

            int num = nums[i];
            Integer count = countMap.get(num);

            Integer endCount = endMap.getOrDefault(num - 1, 0);

            if (endCount < count) {
                return false;
            }

            endMap.put(num, endMap.getOrDefault(num, 0) + count);
        }

        return true;
    }

    public static boolean right(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();
        for (int x : nums) {
            if (!map.containsKey(x)) {
                map.put(x, new PriorityQueue<Integer>());
            }
            if (map.containsKey(x - 1)) {
                int prevLength = map.get(x - 1).poll();
                if (map.get(x - 1).isEmpty()) {
                    map.remove(x - 1);
                }
                map.get(x).offer(prevLength + 1);
            } else {
                map.get(x).offer(1);
            }
        }
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : entrySet) {
            PriorityQueue<Integer> queue = entry.getValue();
            if (queue.peek() < 3) {
                return false;
            }
        }
        return true;
    }

}
