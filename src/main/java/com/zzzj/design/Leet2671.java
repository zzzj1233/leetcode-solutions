package com.zzzj.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-05-11 17:21
 */
public class Leet2671 {

    private static class FrequencyTracker {

        Map<Integer, Integer> freq = new HashMap<>();

        Map<Integer, Integer> cnt = new HashMap<>();

        public FrequencyTracker() {

        }

        public void add(int number) {
            Integer old = cnt.remove(number);

            if (old == null) {
                old = 0;
            }

            int newCnt = old + 1;

            cnt.put(number, newCnt);

            if (freq.containsKey(old)) {
                freq.put(old, freq.get(old) - 1);
            }

            freq.put(newCnt, freq.getOrDefault(newCnt, 0) + 1);
        }

        public void deleteOne(int number) {
            Integer old = cnt.get(number);

            if (old == null || old == 0) {
                return;
            }

            int newCnt = old - 1;

            cnt.put(number, newCnt);

            freq.put(old, freq.get(old) - 1);

            freq.put(newCnt, freq.getOrDefault(newCnt, 0) + 1);
        }

        public boolean hasFrequency(int frequency) {
            return freq.getOrDefault(frequency, 0) > 0;
        }

    }

}
