package com.zzzj.stack;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2023-01-21 21:24
 */
public class Leet2282 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(seePeople(LeetUtils.convertInts("[[1,1],[1,1]]"))));
    }

    public static int[][] seePeople(int[][] heights) {
        int M = heights.length;
        int N = heights[0].length;

        int[][] ans = new int[M][N];

        LinkedList<int[]> stack = new LinkedList<>();

        for (int i = 0; i < M; i++) {

            // 第i行
            for (int j = 0; j < N; j++) {
                int height = heights[i][j];

                while (!stack.isEmpty() && height > heights[stack.peekLast()[0]][stack.peekLast()[1]]) {
                    int[] last = stack.removeLast();
                    ans[last[0]][last[1]]++;
                    if (!stack.isEmpty()) {
                        ans[stack.peekLast()[0]][stack.peekLast()[1]]++;
                    }
                }

                stack.add(new int[]{i, j});
            }

            if (!stack.isEmpty()) {
                stack.removeLast();
            }

            while (!stack.isEmpty()) {
                int[] last = stack.removeLast();
                ans[last[0]][last[1]]++;
            }

        }

        for (int j = 0; j < N; j++) {

            for (int i = 0; i < M; i++) {
                int height = heights[i][j];

                while (!stack.isEmpty() && height > heights[stack.peekLast()[1]][stack.peekLast()[0]]) {
                    int[] last = stack.removeLast();
                    ans[last[1]][last[0]]++;
                    if (!stack.isEmpty()) {
                        ans[stack.peekLast()[1]][stack.peekLast()[0]]++;
                    }
                }

                stack.add(new int[]{i, j});
            }

            if (!stack.isEmpty()) {
                stack.removeLast();
            }

            while (!stack.isEmpty()) {
                int[] last = stack.removeLast();
                ans[last[1]][last[0]]++;
            }

        }


        return ans;
    }

}
