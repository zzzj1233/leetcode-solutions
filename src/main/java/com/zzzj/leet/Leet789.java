package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-09-26 15:22
 */
public class Leet789 {

    public static boolean escapeGhosts(int[][] ghosts, int[] target) {

        int dist = Math.abs(target[0]) + Math.abs(target[1]);

        for (int[] g : ghosts) {

            if (Math.abs(g[0] - target[0]) + Math.abs(g[1] - target[1]) <= dist) {
                return false;
            }

        }

        return true;
    }

}
