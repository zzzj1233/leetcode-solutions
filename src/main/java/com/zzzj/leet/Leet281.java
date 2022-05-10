package com.zzzj.leet;

import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-05-09 16:07
 */
public class Leet281 {

    public static void main(String[] args) {
        final ZigzagIterator iterator = new ZigzagIterator(Arrays.asList(), Arrays.asList(1));

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    static class ZigzagIterator {

        private final List<Integer> v1;
        private final List<Integer> v2;

        int index;
        boolean top;
        final int maxIndex;

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            maxIndex = Math.max(v1.size(), v2.size());
            this.v1 = v1;
            this.v2 = v2;
            this.top = !v1.isEmpty();
        }

        public int next() {
            if (top) {
                int result = v1.get(index);
                if (index < v2.size()) {
                    top = false;
                } else {
                    index++;
                }
                return result;
            } else {
                int result = v2.get(index);
                index++;
                if (index < v1.size()) {
                    top = true;
                }
                return result;
            }
        }

        public boolean hasNext() {
            return index < maxIndex;
        }

    }

}
