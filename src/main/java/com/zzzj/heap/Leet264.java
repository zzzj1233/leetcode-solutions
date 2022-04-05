package com.zzzj.heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Zzzj
 * @create 2022-03-12 11:55
 */
public class Leet264 {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }

    public static int nthUglyNumber(int n) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();


        int i = 1;
        queue.add(1);

        Set<Integer> record = new HashSet<>(100);

        while (i < n) {
            Integer first = queue.remove();
            int m2 = first << 1;
            int m3 = first * 3;
            int m5 = first * 5;

            if (!record.contains(m2)) {
                queue.add(m2);
                record.add(m2);
            }

            if (!record.contains(m3)) {
                queue.add(m3);
                record.add(m3);
            }

            if (!record.contains(m5)) {
                queue.add(m5);
                record.add(m5);
            }

            i++;
        }


        return queue.remove();
    }

    public static int violent(int n) {

        int i = 1;

        Set<Integer> set = new HashSet<>();
        set.add(1);

        int j = 2;
        for (; j < Integer.MAX_VALUE && i < n; j++) {
            int div;
            if (j % 2 == 0) {
                div = j / 2;
            } else if (j % 3 == 0) {
                div = j / 3;
            } else if (j % 5 == 0) {
                div = j / 5;
            } else {
                continue;
            }
            if (set.contains(div)) {
                i++;
                set.add(j);
            }
        }

        return j - 1;
    }

}
