package com.zzzj.design;

import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2022-08-24 19:44
 */
public class Leet2336 {


    private static class SmallestInfiniteSet {

        private TreeSet<Integer> addition = new TreeSet<>();

        int min = 1;

        public SmallestInfiniteSet() {

        }

        public int popSmallest() {
            if (!addition.isEmpty()) {
                return addition.pollFirst();
            }
            return min++;
        }

        public void addBack(int num) {
            if (num < min) {
                addition.add(num);
            }
        }

    }

}
