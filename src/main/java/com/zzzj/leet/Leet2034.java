package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Leet2034 {


    private static class StockPrice {

        Map<Integer, Integer> map = new HashMap<>();

        int newest = -1;
        int newestTime = -1;

        TreeMap<Integer, Integer> rank = new TreeMap<>();

        public StockPrice() {

        }

        public void update(int timestamp, int price) {
            Integer old = map.put(timestamp, price);

            if (timestamp >= newestTime) {
                newestTime = timestamp;
                newest = price;
            }

            if (old != null) {
                Integer oldCount = rank.get(old);
                if (oldCount == 1) {
                    rank.remove(old);
                } else {
                    rank.put(old, oldCount - 1);
                }
            }

            rank.put(price, rank.getOrDefault(price, 0) + 1);
        }

        public int current() {
            return newest;
        }

        public int maximum() {
            return rank.lastEntry().getKey();
        }

        public int minimum() {
            return rank.firstEntry().getKey();
        }
    }

}
