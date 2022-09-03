package com.zzzj.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2022-09-02 18:09
 */
public class Leet981 {


    private static class TimeMap {

        Map<String, TreeMap<Integer, String>> map = new HashMap<>();

        public TimeMap() {

        }

        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, s -> new TreeMap<>())
                    .put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> treeMap = map.get(key);
            if (treeMap == null) {
                return null;
            }
            Map.Entry<Integer, String> entry = treeMap.floorEntry(timestamp);
            return entry == null ? null : entry.getValue();
        }

    }

}
