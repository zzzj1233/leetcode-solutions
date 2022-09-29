package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-26 18:07
 */
public class Leet2120 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(executeInstructions(3, new int[]{0, 1}, "RRDDLU")));
    }

    static final int[][] directions = new int[26][2];

    static {
        directions['R' - 'A'] = new int[]{1, 0};
        directions['L' - 'A'] = new int[]{-1, 0};
        directions['U' - 'A'] = new int[]{0, -1};
        directions['D' - 'A'] = new int[]{0, 1};
    }

    public static int[] executeInstructions(int n, int[] startPos, String s) {

        int[] ans = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            int x = startPos[1];
            int y = startPos[0];

            int j = i;
            for (; j < s.length(); j++) {
                int[] direction = directions[s.charAt(j) - 'A'];

                x += direction[0];
                y += direction[1];

                if (x < 0 || y < 0 || x >= n || y >= n) {
                    break;
                }
            }

            ans[i] = j - i;
        }

        return ans;
    }

}
