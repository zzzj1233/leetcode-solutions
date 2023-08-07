package com.zzzj.contest.week343;

/**
 * @author zzzj
 * @create 2023-08-02 11:14
 */
public class Leet2660 {

    public static void main(String[] args) {

        System.out.println(isWinner(new int[]{5, 6, 1, 10}, new int[]{5, 1, 10, 5}));

        System.out.println(isWinner(new int[]{4, 10, 7, 9}, new int[]{6, 5, 2, 3}));

        System.out.println(isWinner(new int[]{3, 5, 7, 6}, new int[]{8, 10, 10, 2}));

        System.out.println(isWinner(new int[]{2, 3}, new int[]{4, 1}));
    }

    // 22
    // [5,6,1,10]
    // 26
    // [5,1,10,5]
    public static int isWinner(int[] player1, int[] player2) {

        long score1 = 0;

        int preFullScore = -10000;

        for (int i = 0; i < player1.length; i++) {
            int score = player1[i];
            if (i - preFullScore <= 2) {
                score1 += (long) score << 1;
            } else {
                score1 += score;
            }
            preFullScore = score == 10 ? i : preFullScore;
        }

        long score2 = 0;

        preFullScore = -10000;

        for (int i = 0; i < player2.length; i++) {
            int score = player2[i];
            if (i - preFullScore <= 2) {
                score2 += (long) score << 1;
            } else {
                score2 += score;
            }
            preFullScore = score == 10 ? i : preFullScore;
        }

        if (score1 == score2) return 0;
        if (score1 > score2) return 1;
        return 2;
    }

}
