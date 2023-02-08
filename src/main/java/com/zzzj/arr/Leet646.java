package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;

public class Leet646 {

    public static void main(String[] args) {
        System.out.println(
                findLongestChain(
                        LeetUtils.convertInts("[[-10,-8],[8,9],[-5,0],[6,10],[-6,-4],[1,7],[9,10],[-4,7]]")
                )
        );
    }

    public static int findLongestChain(int[][] pairs) {

        Arrays.sort(pairs, Comparator.comparingInt(o -> o[1]));

        int N = pairs.length;
        ;

        int ans = 1;

        int prevEnd = pairs[0][1];

        for (int i = 1; i < N; i++) {
            int start = pairs[i][0];
            if (start > prevEnd) {
                ans++;
                prevEnd = pairs[i][1];
            }
        }

        return ans;
    }

}
