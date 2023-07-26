package com.zzzj.contest.week355;

import java.util.Arrays;
import java.util.List;

public class Leet6955 {

    public static int maxIncreasingGroups(List<Integer> usageLimits) {

        int N = usageLimits.size();

        int[] limit = new int[N];

        for (int i = 0; i < N; i++) limit[i] = usageLimits.get(i);

        Arrays.sort(limit);

        for (int i = 0; i < N; i++) {
        }

        return -1;
    }


}
