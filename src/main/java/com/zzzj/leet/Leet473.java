package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-31 15:40
 */
public class Leet473 {

    public static void main(String[] args) {
//        System.out.println(makesquare(new int[]{5, 5, 5, 5, 16, 4, 4, 4, 4, 4, 3, 3, 3, 3, 4}));
//        System.out.println(makesquare(new int[]{1, 1, 2, 2, 2}));
        System.out.println(makesquare(new int[]{1, 6, 6, 7, 7, 8, 8, 8, 8, 8, 9, 11, 13}));
    }

    public static boolean makesquare(int[] matchsticks) {
        Arrays.sort(matchsticks);

        int sum = Arrays.stream(matchsticks).sum();

        if (sum % 4 != 0) {
            return false;
        }

        int perEdge = sum / 4;

        if (matchsticks[matchsticks.length - 1] > perEdge) {
            return false;
        }

        boolean[] used = new boolean[matchsticks.length];

        int edge = 4;

        for (int i = 0; i < matchsticks.length; i++) {
            if (matchsticks[i] == perEdge) {
                used[i] = true;
                edge--;
            }
        }

        return dfs(matchsticks, perEdge, used, edge, 0, matchsticks.length - 1);
    }

    public static boolean dfs(int[] matchsticks, int perEdge, boolean[] used, int edge, int cur, int start) {
        if (edge == 0) {
            for (boolean use : used) {
                if (!use) {
                    return false;
                }
            }
            return true;
        }


        if (cur == perEdge) {
            return dfs(matchsticks, perEdge, used, edge - 1, 0, matchsticks.length - 1);
        }

        // 火柴无法折断,也就是说,只能拼成maxSize的倍数
        for (int i = start; i >= 0; i--) {
            if (used[i]) {
                continue;
            }

            if (cur + matchsticks[i] > perEdge) {
                continue;
            }

            used[i] = true;

            if (dfs(matchsticks, perEdge, used, edge, cur + matchsticks[i], i - 1)) {
                return true;
            }

            used[i] = false;

            while (i - 1 >= 0 && matchsticks[i - 1] == matchsticks[i]) {
                i--;
            }
        }

        return false;
    }


}
