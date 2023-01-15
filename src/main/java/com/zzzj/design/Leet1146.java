package com.zzzj.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Leet1146 {

    public static void main(String[] args) {
        SnapshotArray arr = new SnapshotArray(3);
        arr.set(0, 1);
        arr.set(1, 2);
        arr.snap();
        arr.set(1, 3);
        arr.set(2, 4);
        arr.snap();

        System.out.println(arr.get(0, 0));
        System.out.println(arr.get(0, 1));

        System.out.println(arr.get(1, 0));
        System.out.println(arr.get(1, 1));

        // System.out.println(arr.get(2, 0));
        System.out.println(arr.get(2, 1));
    }

    private static class SnapshotArray {

        Map<Integer, TreeMap<Integer, Integer>> map;

        int id;

        public SnapshotArray(int length) {
            map = new HashMap<>();
        }

        public void set(int index, int val) {
            map.computeIfAbsent(index, integer -> new TreeMap<>()).put(id, val);
        }

        public int snap() {
            id++;
            return id - 1;
        }

        public int get(int index, int snap_id) {
            TreeMap<Integer, Integer> treeMap = map.get(index);
            if (treeMap == null) {
                return 0;
            }
            Map.Entry<Integer, Integer> entry = treeMap.floorEntry(snap_id);
            if (entry == null) {
                return 0;
            }
            return entry.getValue();
        }
    }

}
