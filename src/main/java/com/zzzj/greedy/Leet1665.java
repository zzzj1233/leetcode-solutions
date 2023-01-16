package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

public class Leet1665 {


    public static void main(String[] args) {
        System.out.println(minimumEffort(LeetUtils.convertInts("[[1,3],[2,4],[10,11],[10,12],[8,9]]")));
    }

    public static int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (o1, o2) -> {
            return (o2[1] - o2[0]) - (o1[1] - o1[0]);
        });

        int power = 0;

        int ans = 0;

        for (int[] task : tasks) {
            if (power < task[1]) {
                ans += task[1] - power;
                power = task[1];
            }
            power -= task[0];
        }

        return ans;
    }


}
