package com.zzzj.contest.week356;

public class Leet6917 {


    public static int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int ans = 0;

        for (int hour : hours) {
            if (hour >= target) ans++;
        }

        return ans;
    }

}
