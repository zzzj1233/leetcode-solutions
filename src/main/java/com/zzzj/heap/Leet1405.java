package com.zzzj.heap;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Zzzj
 * @create 2022-03-12 19:44
 */
public class Leet1405 {

    public static void main(String[] args) {

        int n = 10;

        System.out.println(longestDiverseString(4, 4, 3));

        System.exit(0);

        // bbabbabbaabb
        // bbaabbabbab
        for (int i = 0; i < 10000; i++) {
            int a = LeetUtils.random.nextInt(n);
            int b = LeetUtils.random.nextInt(n);
            int c = LeetUtils.random.nextInt(n);
            if (longestDiverseString(a, b, c).length() != right(a, b, c).length()) {
                System.out.println(a);
                System.out.println(b);
                System.out.println(c);
                System.out.println(longestDiverseString(a, b, c));
                return;
            }
        }
    }

    public static String longestDiverseString(int a1, int b1, int c1) {

        Item a = new Item('a', a1);
        Item b = new Item('b', b1);
        Item c = new Item('c', c1);

        PriorityQueue<Item> queue = new PriorityQueue<>(3, (o1, o2) -> o2.count - o1.count);

        if (a1 > 0) {
            queue.add(a);
        }
        if (b1 > 0) {
            queue.add(b);
        }
        if (c1 > 0) {
            queue.add(c);
        }

        StringBuilder builder = new StringBuilder();

        while (!queue.isEmpty()) {
            Item max = queue.remove();

            int count = Math.min(2, max.count);

            for (int i = 0; i < count; i++) {
                builder.append(max.c);
            }

            if (queue.isEmpty()) {
                return builder.toString();
            }

            builder.append(queue.peek().c);

            queue.peek().count -= 1;

            if (queue.peek().count <= 0) {
                queue.remove();
            }

            max.count -= count;

            if (max.count > 0) {
                queue.add(max);
            }

        }

        return builder.toString();
    }

    static class Item {
        char c;
        int count;

        public Item(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }


    public static String right(int a, int b, int c) {
        StringBuilder res = new StringBuilder();
        Pair[] arr = {new Pair(a, 'a'), new Pair(b, 'b'), new Pair(c, 'c')};

        while (true) {
            Arrays.sort(arr, (p1, p2) -> p2.freq - p1.freq);
            boolean hasNext = false;
            for (Pair pair : arr) {
                if (pair.freq <= 0) {
                    break;
                }
                int m = res.length();
                if (m >= 2 && res.charAt(m - 2) == pair.ch && res.charAt(m - 1) == pair.ch) {
                    continue;
                }
                hasNext = true;
                res.append(pair.ch);
                pair.freq--;
                break;
            }
            if (!hasNext) {
                break;
            }
        }

        return res.toString();
    }

    static class Pair {
        int freq;
        char ch;

        public Pair(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }
    }


}
