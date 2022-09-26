package com.zzzj.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-09-19 18:19
 */
public class Leet646 {

    public static int findLongestChain(int[][] pairs) {

        // pairs[i].len == 2
        // pairs[0] < pairs[1]

        Arrays.sort(pairs, Comparator.comparingInt(o -> o[0]));

        int ans = 1;

        int cur = pairs[0][0];


        return ans;
    }

}
