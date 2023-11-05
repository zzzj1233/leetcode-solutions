package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-05-17 11:06
 */
public class Leet1072 {

    public static void main(String[] args) {
        System.out.println(maxEqualRowsAfterFlips(
                LeetUtils.convertInts("[[0,0,0],[0,0,1],[1,1,0]]")
        ));
    }

    public static int maxEqualRowsAfterFlips(int[][] matrix) {

        int M = matrix.length;

        int N = matrix[0].length;

        int ans = 0;

        for (int i = 0; i < M; i++) {

            int cnt = 1;

            for (int j = i + 1; j < M; j++) {
                if (Arrays.equals(matrix[i], matrix[j])) {
                    cnt++;
                } else {
                    boolean match = true;
                    for (int k = 0; k < N; k++) {
                        if (matrix[i][k] == matrix[j][k]) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        cnt++;
                    }
                }
                ans = Math.max(ans, cnt);
            }

        }

        return ans;
    }

}
