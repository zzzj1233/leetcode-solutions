package com.zzzj.stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Zzzj
 * @create 2021-12-13 20:58
 */
public class Leet85 {


    public static void main(String[] args) {
        System.out.println(maximalRectangle(new int[][]{{0, 1, 1, 0, 1}, {1, 1, 0, 1, 0}, {0, 1, 1, 1, 0}, {1, 1, 1, 1, 0}, {1, 1, 1, 1, 1}}));
    }

    // 1 0 1 0 0    1 0 1 0 0
    // 1 0 1 1 1    2 0 2 1 1
    // 1 1 1 1 1    3 1 3 2 2
    // 1 0 0 1 0    4 0 0 3 0
    public static int maximalRectangle(int[][] matrix) {

        int[] height = new int[matrix[0].length];

        int answer = -1;

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    height[j] = 0;
                } else if (i > 0) {
                    height[j] = height[j] + matrix[i][j];
                } else {
                    height[j] = matrix[i][j];
                }
            }
            answer = Math.max(answer, f(height));

        }

        return answer;
    }

    private static int f(int[] line) {
        int ans = 0;
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < line.length; i++) {
            if (!s.isEmpty() && line[s.peek()] >= line[i]) {
                int right = s.peek();
                while (!s.isEmpty() && line[s.peek()] >= line[i]) {
                    int index = s.pop();
                    int left = s.isEmpty() ? -1 : s.peek();
                    ans = Math.max(ans, (right - left) * line[index]);
                }
            }
            s.add(i);
        }
        int right = s.isEmpty() ? 0 : s.peek();
        while (!s.isEmpty()) {
            int index = s.pop();
            int left = s.isEmpty() ? -1 : s.peek();
            ans = Math.max(ans, (right - left) * line[index]);
        }
        return ans;
    }


    private static int calcMax(int[] height) {
        LinkedList<Integer> stack = new LinkedList<>();

        stack.add(0);

        int max = -1;



        return max;
    }

}
