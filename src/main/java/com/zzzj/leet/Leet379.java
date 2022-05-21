package com.zzzj.leet;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-05-17 15:32
 */
public class Leet379 {

    static class PhoneDirectory {

        private final int maxNumbers;

        LinkedList<Integer> list;

        private int max;

        Set<Integer> used;

        public PhoneDirectory(int maxNumbers) {
            list = new LinkedList<>();
            used = new HashSet<>();
            this.maxNumbers = maxNumbers;
        }

        public int get() {
            if (list.isEmpty()) {
                if (max == maxNumbers) {
                    return -1;
                }
                list.add(max++);
            }
            Integer first = list.removeFirst();
            used.add(first);
            return first;
        }

        public boolean check(int number) {
            return !used.contains(number);
        }

        public void release(int number) {
            if (!used.remove(number)) {
                return;
            }
            list.addFirst(number);
        }
    }

}
