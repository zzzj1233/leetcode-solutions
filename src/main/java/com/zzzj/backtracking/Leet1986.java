package com.zzzj.backtracking;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-28 16:29
 */
public class Leet1986 {

    public static void main(String[] args) {
        System.out.println(minSessions(new int[]{1}, 2));
    }

    static int ans;

    static int all;

    public static int minSessions(int[] tasks, int sessionTime) {
        ans = Integer.MAX_VALUE;

        Arrays.sort(tasks);

        int N = tasks.length;

        for (int i = 0; i < N; i++) {
            all |= 1 << i;
        }

        dfs(tasks, sessionTime, sessionTime, 0, 1);

        return ans;
    }

    public static boolean isFinished(int finished, int index) {
        return ((finished >> index) & 1) == 1;
    }

    public static int markFinished(int finished, int index) {
        finished |= 1 << index;
        return finished;
    }

    public static void dfs(int[] tasks, int time, int sessionTime, int finished, int count) {
        if ((finished & all) == all) {
            ans = Math.min(ans, count);
            return;
        }
        for (int i = 0; i < tasks.length; i++) {
            if (isFinished(finished, i)) {
                continue;
            }
            int task = tasks[i];

            if (time < task) {
                dfs(tasks, sessionTime - task, sessionTime, markFinished(finished, i), count + 1);
            } else {
                dfs(tasks, time - task, sessionTime, markFinished(finished, i), count);
            }

        }
    }

}
