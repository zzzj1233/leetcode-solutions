package com.zzzj.greedy;


import java.util.Arrays;

public class Leet2410 {

    public static int matchPlayersAndTrainers(int[] players, int[] trainers) {

        Arrays.sort(players);

        Arrays.sort(trainers);

        int index1 = 0;
        int index2 = 0;

        int N = players.length;
        int M = trainers.length;

        int ans = 0;

        while (index1 < N && index2 < M) {
            int player = players[index1];

            int trainer = trainers[index2];

            if (player <= trainer) {
                ans++;
                index1++;
                index2++;
            } else {
                index2++;
            }
        }

        return ans;
    }

    public static void reverse(int[] original) {
        int LEFT = 0;
        int RIGHT = original.length - 1;

        while (LEFT < RIGHT) {
            int temp = original[LEFT];
            original[LEFT] = original[RIGHT];
            original[RIGHT] = temp;
            LEFT++;
            RIGHT--;
        }
    }

}
