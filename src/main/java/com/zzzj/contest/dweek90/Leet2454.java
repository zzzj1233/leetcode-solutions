package com.zzzj.contest.dweek90;


import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2023-09-01 11:11
 */
public class Leet2454 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(secondGreaterElement(new int[]{2, 4, 0, 9, 6})));

        System.out.println(Arrays.toString(secondGreaterElement(new int[]{5, 1, 2, 3, 4, 9, 6})));

    }

    public static int[] secondGreaterElement(int[] nums) {

        int N = nums.length;

        LinkedList<Integer> queue = new LinkedList<>();

        PriorityQueue<Integer>[] qs = new PriorityQueue[N];

        int[] ans = new int[N];

        Arrays.fill(ans, -1);

        Comparator<Integer> comparator = Comparator.comparingInt(o -> nums[o]);

        for (int i = 0; i < N; i++) {
            int num = nums[i];

            while (!queue.isEmpty() && nums[queue.peekLast()] < num) {
                Integer rm = queue.removeLast();

                if (qs[rm] != null) {
                    while (!qs[rm].isEmpty()) {
                        ans[qs[rm].remove()] = num;
                    }
                    qs[rm] = null;
                }

                if (qs[i] == null)
                    qs[i] = new PriorityQueue<>(comparator);
                qs[i].add(rm);
            }

            if (!queue.isEmpty() && qs[queue.peekLast()] != null) {
                PriorityQueue<Integer> q = qs[queue.peekLast()];
                while (!q.isEmpty() && nums[q.peek()] < num) {
                    ans[q.remove()] = num;
                }
            }

            queue.add(i);
        }

        return ans;
    }

}
