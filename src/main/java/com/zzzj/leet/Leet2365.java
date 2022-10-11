package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-10-10 17:58
 */
public class Leet2365 {

    public static void main(String[] args) {
        System.out.println(taskSchedulerII(new int[]{1, 2, 1, 2, 3, 1}, 3));
    }

    public static long taskSchedulerII(int[] tasks, int space) {
        // 相同的任务需要间隔space天

        // 需要按顺序完成任务

        // 每天可以 : 完成一个任务 / 休息一天

        long ans = 0;

        Map<Integer, Integer> map = new HashMap<>();

        int N = tasks.length;

        int day = 0;
        for (int i = 0; i < N; i++, day++) {
            int task = tasks[i];
            // 是否可以做任务
            // 当前是第i天
            Integer schedule = map.get(task);

            if (schedule != null && day <= schedule) {
                ans += schedule - day + 1;
                day += schedule - day + 1;
            }

            ans += 1;

            map.put(task, day + space);
        }

        return ans;
    }

}
