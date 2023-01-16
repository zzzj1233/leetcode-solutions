package com.zzzj.greedy;

import java.util.Arrays;

public class Leet2323 {

    public static int minimumTime(int[] jobs, int[] workers) {

        int N = jobs.length;

        Arrays.sort(jobs);
        Arrays.sort(workers);

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int day = (int) Math.ceil(jobs[i] / workers[i]);
            ans = Math.max(ans, day);
        }

        return ans;
    }

}
