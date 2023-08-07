package com.zzzj.contest.week344;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-01 18:05
 */
public class Leet2672 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(colorTheArray(4, LeetUtils.convertInts("[[0,2],[1,2],[3,1],[1,1],[2,1]]"))));

        System.out.println(Arrays.toString(colorTheArray(1, LeetUtils.convertInts("[[0,100000]]"))));
    }

    public static int[] colorTheArray(int n, int[][] queries) {

        int N = queries.length;

        int[] ans = new int[N];

        int[] colors = new int[n + 1];
        colors[n] = -1;

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            int index = queries[i][0];
            int color = queries[i][1];
            int original = colors[index];
            colors[index] = color;
            if (index - 1 >= 0) {
                if (original != 0 && colors[index - 1] == original)
                    cnt--;
                if (colors[index - 1] == color)
                    cnt++;
            }
            if (original != 0 && colors[index + 1] == original)
                cnt--;
            if (colors[index + 1] == color) {
                cnt++;
            }

            ans[i] = cnt;
        }

        return ans;
    }

}
