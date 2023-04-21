package com.zzzj.greedy;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-02-28 16:58
 */
public class Leet2434 {

    public static void main(String[] args) {
        System.out.println(robotWithString("bdda"));
    }

    private static class Item {
        char c;
        int count;

        public Item(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    public static String robotWithString(String s) {

        int N = s.length();

        Map<Character, Item> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            Item item = map.computeIfAbsent(c, character -> new Item(c, 0));
            item.count++;
        }

        PriorityQueue<Item> pq = new PriorityQueue<>(map.size(), Comparator.comparingInt(o -> o.c));

        pq.addAll(map.values());

        StringBuilder builder = new StringBuilder(N);

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            list.add(i);
        }

        while (!list.isEmpty()) {
            Iterator<Integer> it = list.iterator();

            while (it.hasNext()) {
                Integer idx = it.next();

                char c = s.charAt(idx);

                Item peek = pq.peek();

                if (c == peek.c) {
                    if (peek.count == 1) {
                        pq.remove();
                    } else {
                        peek.count--;
                    }
                    it.remove();
                    builder.append(c);
                    break;
                }
            }
        }

        return builder.toString();
    }

}
