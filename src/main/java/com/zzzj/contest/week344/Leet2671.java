package com.zzzj.contest.week344;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-08-01 17:55
 */
public class Leet2671 {

    public static void main(String[] args) {
    }

    private static class FrequencyTracker {

        private final Map<Integer, Integer> freq;

        private final Map<Integer, Integer> cnt;

        public FrequencyTracker() {
            freq = new HashMap<>();

            cnt = new HashMap<>();
        }

        public void add(int number) {

            Integer oldCnt = cnt.getOrDefault(number, 0);

            int newCnt = oldCnt + 1;

            cnt.put(number, newCnt);

            if (freq.containsKey(oldCnt)) {

                Integer oldFreq = freq.get(oldCnt);

                if (oldFreq == 1)
                    freq.remove(oldCnt);
                else
                    freq.put(oldCnt, oldFreq - 1);
            }

            freq.put(newCnt, freq.getOrDefault(newCnt, 0) + 1);
        }

        public void deleteOne(int number) {

            Integer oldCnt = cnt.get(number);

            if (oldCnt == null) return;

            int newCnt = oldCnt - 1;

            if (oldCnt == 1) {
                cnt.remove(number);
            } else {
                cnt.put(number, newCnt);
            }

            Integer oldFreq = freq.get(oldCnt);

            if (oldFreq != null) {
                if (oldFreq == 1)
                    freq.remove(oldCnt);
                else
                    freq.put(oldCnt, oldFreq - 1);
            }

            freq.put(newCnt, freq.getOrDefault(newCnt, 0) + 1);
        }

        public boolean hasFrequency(int frequency) {
            return freq.containsKey(frequency);
        }

    }

}
