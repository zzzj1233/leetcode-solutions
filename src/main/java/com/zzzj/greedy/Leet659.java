package com.zzzj.greedy;

import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.*;

/**
 * 太难了,写不出来
 * <p>
 * 这应该是道Hard题
 *
 * @author zzzj
 * @create 2022-11-01 11:59
 */
public class Leet659 {

    public static void main(String[] args) {

        System.out.println(isPossible(new int[]{2, 3, 4, 5, 6, 7, 7, 8, 8, 9}));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {
//            int N = LeetUtils.random.nextInt(100);

            int N = 10;

            int[] arr = ArrayUtil.generateArray(N, 2, 10);

            ArrayCopyIterator iterator = new ArrayCopyIterator(arr, true);

            if (isPossible(iterator.next()) != right(iterator.next())) {
                System.out.println("Error --- " + i);
                System.out.println(Arrays.toString(iterator.next()));
                System.out.println("MyAns : " + isPossible(iterator.next()));
                System.out.println("Right : " + right(iterator.next()));
                return;
            }

        }

        System.out.println("Ok");
    }

    public static boolean need(Integer count, int value, TreeMap<Integer, Integer> map) {
        if (count == null) {
            return false;
        }

        int c1 = 0;
        int c3 = 0;

        if (!map.containsKey(value - 3) && map.containsKey(value - 2) && map.containsKey(value - 1)) {
            c1 = map.get(value - 1);
        }

        if (!map.containsKey(value + 3) && map.containsKey(value + 1) && map.containsKey(value + 2)) {
            c3 = map.get(value + 1);
        }


        int c2 = 0;

        // 没有value - 2,但是有value - 1
        if (!map.containsKey(value - 2) && map.containsKey(value - 1)) {
            // value - 1 , value , value + 1
            c2 = map.get(value - 1);


        }

        if (!map.containsKey(value + 2) && map.containsKey(value + 1)) {
            Integer v1 = map.get(value + 1);
            c2 = Math.max(c2, v1);
        }

        return c1 + c3 < count && c2 < count;
    }


    public static boolean isPossible(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();


        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        while (map.size() >= 3) {
            Map.Entry<Integer, Integer> min1 = map.pollFirstEntry();
            Map.Entry<Integer, Integer> min2 = map.pollFirstEntry();
            Map.Entry<Integer, Integer> min3 = map.pollFirstEntry();

            if (min2.getKey() != min1.getKey() + 1 || min3.getKey() != min2.getKey() + 1) {
                return false;
            }

            if (min1.getValue() - 1 > 0) {
                map.put(min1.getKey(), min1.getValue() - 1);
            }

            if (min2.getValue() - 1 > 0) {
                map.put(min2.getKey(), min2.getValue() - 1);
            }

            if (min3.getValue() - 1 > 0) {
                map.put(min3.getKey(), min3.getValue() - 1);
            }

            int nextValue = min3.getKey() + 1;

            Integer count = map.get(nextValue);

            while (need(count, nextValue, map)) {
                if (count - 1 > 0) {
                    map.put(nextValue, count - 1);
                } else {
                    map.remove(nextValue);
                }
                nextValue++;

                count = map.get(nextValue);
            }
        }

        return map.isEmpty();
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
