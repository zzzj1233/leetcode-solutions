package com.zzzj.leet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zzzj
 * @create 2024-01-10 15:02
 */
public class Leet1606 {

    public static void main(String[] args) {

        System.out.println(busiestServers(2, new int[]{1, 2, 3}, new int[]{1000000000, 1, 1000000000}));


    }

    public static List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> free = new TreeSet<>();

        for (int i = 0; i < k; i++)
            free.add(i);

        int[] end = new int[k];

        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.comparingInt(o -> end[o]));

        int[] times = new int[k];

        int N = arrival.length;

        for (int i = 0; i < N; i++) {

            while (!queue.isEmpty() && end[queue.peek()] <= arrival[i])
                free.add(queue.remove());

            if (free.isEmpty())
                continue;

            int workMachine;

            Integer ceiling = free.ceiling(i % k);

            if (ceiling != null)
                workMachine = ceiling;
            else
                workMachine = free.first();

            if (workMachine == -1)
                continue;

            free.remove(workMachine);
            end[workMachine] = arrival[i] + load[i];
            queue.add(workMachine);
            times[workMachine]++;
        }

        int max = Arrays.stream(times).max().orElse(0);

        return IntStream.range(0, k).filter(index -> times[index] == max).boxed().collect(Collectors.toList());
    }

}
