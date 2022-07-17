package com.zzzj.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Zzzj
 * @create 2022-03-17 17:28
 */
public class Leet692 {


    public static void main(String[] args) {
        System.out.println(topKFrequent(new String[]{"a", "aa", "aaa"}, 2));

        System.out.println(topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 1));
        System.out.println(topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
    }

    static class Item {
        String str;
        int count;

        public Item(String str, int count) {
            this.str = str;
            this.count = count;
        }

    }

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Item> map = new HashMap<>(words.length);

        for (String word : words) {
            Item item = map.get(word);
            if (item == null) {
                item = new Item(word, 0);
                map.put(word, item);
            }
            item.count++;
        }

        // 小根堆
        PriorityQueue<Item> queue = new PriorityQueue<>(k, (o1, o2) -> {
            if (o1.count == o2.count) {
                return o2.str.compareTo(o1.str);
            }
            return o1.count - o2.count;
        });

        for (Map.Entry<String, Item> entry : map.entrySet()) {

            Item item = entry.getValue();

            if (queue.size() < k) {
                queue.add(item);
            } else {
                queue.add(item);
                queue.remove();
            }
        }

        LinkedList<String> list = new LinkedList<>();

        while (!queue.isEmpty()) {
            list.addFirst(queue.remove().str);
        }

        return list;
    }

}
