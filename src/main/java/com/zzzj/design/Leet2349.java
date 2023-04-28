package com.zzzj.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2023-04-26 18:20
 */
public class Leet2349 {


    private static class NumberContainers {

        Map<Integer, TreeSet<Integer>> numberMap = new HashMap<>();

        Map<Integer, Integer> positionMap = new HashMap<>();

        public NumberContainers() {

        }

        public void change(int index, int number) {
            Integer oldValue = positionMap.put(index, number);

            if (oldValue != null) {
                numberMap.get(oldValue).remove(index);
            }

            numberMap.computeIfAbsent(number, notUsed -> new TreeSet<>())
                    .add(index);
        }

        public int find(int number) {
            TreeSet<Integer> set = numberMap.get(number);

            if (set == null || set.first() == null) {
                return -1;
            }

            return set.first();
        }

    }

}
