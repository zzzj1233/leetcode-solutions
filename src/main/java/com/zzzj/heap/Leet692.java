package com.zzzj.heap;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-03-17 17:28
 */
public class Leet692 {


    public static void main(String[] args) {
        System.out.println(topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
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
        Map<String, Integer> map = new HashMap<>(words.length);

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Item> queue = new PriorityQueue<>(map.size(), Comparator.comparingInt(o -> ((Item) o).count).reversed().thenComparing(o -> ((Item) o).str));

        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            queue.add(new Item(stringIntegerEntry.getKey(), stringIntegerEntry.getValue()));
        }

        List<String> ans = new ArrayList<>(k);

        for (int i = 0; i < k; i++) {
            ans.add(queue.remove().str);
        }

        return ans;
    }

}
