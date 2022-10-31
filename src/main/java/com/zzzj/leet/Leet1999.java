package com.zzzj.leet;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Leet1999 {

    public static void main(String[] args) {
        System.out.println(findInteger(5, 1, 1));
    }

    public static int findInteger(int k, int digit1, int digit2) {

        PriorityQueue<String> queue = new PriorityQueue<>((o1, o2) -> Long.valueOf(o1).compareTo(Long.parseLong(o2)));

        Set<String> used = new HashSet<>();

        queue.add(String.valueOf(digit1));
        used.add(queue.peek());
        queue.add(String.valueOf(digit2));
        used.add(queue.peek());


        while (!queue.isEmpty()) {
            String remove = queue.remove();

            long min = Long.parseLong(remove);

            if (min > Integer.MAX_VALUE) {
                return -1;
            }

            if (min % k == 0 && min > k) {
                return (int) min;
            }


            add(used, digit1 + remove, queue);
            add(used, digit2 + remove, queue);
        }

        return -1;
    }

    public static void add(Set<String> set, String value, PriorityQueue<String> queue) {
        if (value.length() < 12 && set.add(value)) {
            queue.add(value);
        }
    }

}
