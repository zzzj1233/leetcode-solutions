package com.zzzj.backtracking;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-06-13 14:55
 */
public class Leet2212 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maximumBobPoints(9, new int[]{1, 1, 0, 1, 0, 0, 2, 1, 0, 1, 2, 0})));

        System.out.println(Arrays.toString(maximumBobPoints(3, new int[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2})));

        System.out.println(Arrays.toString(maximumBobPoints(89, new int[]{3, 2, 28, 1, 7, 1, 16, 7, 3, 13, 3, 5})));
    }

    public static int[] maximumBobPoints(int numArrows, int[] aliceArrows) {

        maxState = 0;
        maxScore = 0;

        dfs(numArrows, aliceArrows, 0, 0, 0);

        int N = aliceArrows.length;

        int[] ans = new int[N];

        for (int i = N - 1; i >= 0; i--) if (((maxState >> i) & 1) == 1) ans[i] = aliceArrows[i] + 1;

        int sum = Arrays.stream(ans).sum();

        if (sum < numArrows)
            for (int i = 0; i < N; i++)
                if (ans[i] != 0) {
                    ans[i] += numArrows - sum;
                    break;
                }


        return ans;
    }

    static int maxScore;

    static int maxState;

    public static void dfs(int numArrows, int[] aliceArrows, int index, int state, int score) {

        if (index >= aliceArrows.length) {
            if (score > maxScore) {
                maxState = state;
                maxScore = score;
            }
            return;
        }

        if (numArrows <= 0) {
            dfs(0, aliceArrows, index + 1, state, score);
            return;
        }

        int aliceArrow = aliceArrows[index];

        if (numArrows > aliceArrow) dfs(numArrows - aliceArrow - 1, aliceArrows, index + 1, state | (1 << index), score + index);

        dfs(numArrows, aliceArrows, index + 1, state, score);
    }

}
