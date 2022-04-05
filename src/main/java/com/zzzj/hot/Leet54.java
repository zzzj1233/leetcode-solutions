package com.zzzj.hot;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.Unresolved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-04-05 21:12
 */
@Unresolved
public class Leet54 {

    public static void main(String[] args) {

        System.out.println(spiralOrder(LeetUtils.convertInts("[[7],[9],[6]]")));

        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            int[][] matrix = LeetUtils.randomMatrix(20, 20, 0, 100);
            if (!spiralOrder(matrix).equals(right(matrix))) {
                System.out.println("Error");
                System.out.println(Arrays.deepToString(matrix));
                System.out.println(spiralOrder(matrix));
                return;
            }
        }
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>(matrix.length * matrix[0].length);

        int tr = 0;
        int tc = 0;
        int br = matrix.length - 1;
        int bc = matrix[0].length - 1;

        while (tr <= br && tc <= bc) {

            int row = br - tr;
            int col = bc - tc;

            for (int i = 0; i <= col; i++) {
                ans.add(matrix[tr][tc + i]);
            }

            for (int i = 1; i <= row; i++) {
                ans.add(matrix[tr + i][bc]);
            }

            for (int i = col - 1; row > 0 && i >= 0; i--) {
                ans.add(matrix[br][tc + i]);
            }

            for (int i = row - 1; i > 0; i--) {
                ans.add(matrix[tr + i][tc]);
            }

            tr++;
            br--;
            tc++;
            bc--;
        }

        return ans;
    }

    public static List<Integer> right(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

}
