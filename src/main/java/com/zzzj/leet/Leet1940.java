package com.zzzj.leet;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-09-09 14:22
 */
public class Leet1940 {

    // [
    //  [1,2,3,4,5,6,7,9,10],
    //  [1,3,4,5,7,8,9,10],
    //  [1,2,6,7,8,10],
    //  [1,2,3,4,5,6,7,8,9,10],
    //  [2,4,5,6,7,8,9,10],
    //  [2,6,7,8,9]
    // ]
    public static List<Integer> longestCommonSubsequence(int[][] arrays) {

        boolean[] bucket = new boolean[101];

        for (int i = 0; i < arrays[0].length; i++) {
            bucket[arrays[0][i]] = true;
        }

        int N = arrays.length;

        for (int i = 1; i < N; i++) {

            int[] array = arrays[i];

            int M = array.length;

            for (int j = 0; j < M; j++) {
                int prev = j - 1 >= 0 ? array[j - 1] : 0;
                int cur = array[j];

                for (int k = prev + 1; k < cur; k++) {
                    if (bucket[k]) {
                        bucket[k] = false;
                    }
                }

            }

            int last = arrays[i][M - 1];

            for (int k = last + 1; k < 101; k++) {
                if (bucket[k]) {
                    bucket[k] = false;
                }
            }

        }


        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < 101; i++) {
            if (bucket[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

}
