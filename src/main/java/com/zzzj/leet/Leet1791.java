package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-03-30 14:57
 */
public class Leet1791 {

    public static int findCenter(int[][] edges) {
        int[] first = edges[0];

        int[] last = edges[edges.length - 1];

        if (first[0] == last[0]) {
            return first[0];
        } else if (first[0] == last[1]) {
            return first[0];
        }
        return first[1];
    }

}
