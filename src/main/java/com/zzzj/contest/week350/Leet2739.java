package com.zzzj.contest.week350;

public class Leet2739 {

    public static void main(String[] args) {
        System.out.println(distanceTraveled(9, 2));
    }


    public static int distanceTraveled(int mainTank, int additionalTank) {

        int ans = 0;

        int cnt = 0;

        while (mainTank > 0) {
            ans += 10;

            mainTank -= 1;

            cnt += 1;

            if (cnt == 5 && additionalTank > 0) {
                mainTank += 1;
                additionalTank -= 1;
                cnt = 0;
            }
        }

        return ans;
    }

}
