package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.Unresolved;

import java.util.Arrays;
import java.util.Comparator;

@Unresolved
public class Leet646 {

    public static void main(String[] args) {
        System.out.println(findLongestChain(LeetUtils.convertInts("[[1,2], [2,3], [3,4]]")));
    }

    public static int findLongestChain(int[][] pairs) {

        Arrays.sort(pairs, Comparator.comparingInt(o -> o[1]));

        int N = pairs.length;

        int ans = 0;

        for (int i = N - 1; i >= 0; i--) {

            int[] pair = pairs[i];

            int c = 0;

            for (int j = 0; j < pairs.length; j++) {
                if (pair[1] < pairs[j][0]) {
                    c++;
                }
            }

            ans = Math.max(ans, c + 1);

        }

        return ans;
    }

}
