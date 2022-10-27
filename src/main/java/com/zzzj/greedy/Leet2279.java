package com.zzzj.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-10-12 11:37
 */
public class Leet2279 {

    public static int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {

        int N = capacity.length;

        Info[] infos = new Info[N];

        for (int i = 0; i < N; i++) {
            infos[i] = new Info(capacity[i], rocks[i]);
        }

        Arrays.sort(infos, Comparator.comparingInt(Info::less));

        int ans = 0;

        for (int i = 0; i < N && additionalRocks > 0; i++) {
            int less = infos[i].less();
            if (less == 0) {
                ans++;
            } else {
                if (additionalRocks >= less) {
                    ans++;
                }
                additionalRocks -= less;
            }
        }

        return ans;
    }

    private static class Info {
        int capacity;
        int rock;

        public int less() {
            return capacity - rock;
        }

        public Info(int capacity, int rock) {
            this.capacity = capacity;
            this.rock = rock;
        }
    }

}
