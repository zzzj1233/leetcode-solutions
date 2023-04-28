package com.zzzj.design;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-04-26 11:17
 */
public class Leet2502 {

    private static class Allocator {

        private final int n;

        private Map<Integer, List<Integer>> midRecord;

        private TreeMap<Integer, Integer> used;

        public Allocator(int n) {
            midRecord = new HashMap<>();
            used = new TreeMap<>();
            used.put(n, 0);
            this.n = n;
        }

        public int allocate(int size, int mID) {
            int start = 0;

            while (start != n) {
                Map.Entry<Integer, Integer> ceiling = used.ceilingEntry(start);
                // 可以放
                if (ceiling.getKey() - start >= size) {
                    used.put(start, size);
                    midRecord.computeIfAbsent(mID, notUse -> new ArrayList<>())
                            .add(start);
                    return start;
                } else {
                    start = ceiling.getKey() + ceiling.getValue();
                }
            }

            return -1;
        }

        public int free(int mID) {
            List<Integer> list = midRecord.get(mID);

            if (list == null || list.isEmpty()) {
                return 0;
            }

            int sum = 0;

            for (Integer index : list) {
                sum += used.remove(index);
            }

            midRecord.remove(mID);

            return sum;
        }

    }

}
