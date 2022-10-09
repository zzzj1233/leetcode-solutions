package com.zzzj.leet;

public class Leet1701 {

    public static void main(String[] args) {
        System.out.println(averageWaitingTime(LeetUtils.convertInts(" [[5,2],[5,4],[10,3],[20,1]]")));
    }

    public static double averageWaitingTime(int[][] customers) {
        int N = customers.length;

        if (N == 0) {
            return 0;
        }

        int nextTime = customers[0][0];

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int[] customer = customers[i];
            int arrive = customer[0];
            int cost = customer[1];

            nextTime = Math.max(nextTime, arrive);

            int finishTime = nextTime + cost;
            ans += finishTime - arrive;
            nextTime = finishTime;
        }

        return (double) ans / N;
    }

}
