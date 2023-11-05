package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-12-19 13:11
 */
public class Leet74 {

    public static void main(String[] args) {
        System.out.println(searchMatrix(LeetUtils.convertInts("[[1,1]]"), 2));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {

        int M = matrix.length;

        int N = matrix[0].length;

        int left = 0;
        int right = M - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (matrix[mid][0] > target) {
                right = mid - 1;
            } else {
                // <= target
                int l = 0;
                int r = N - 1;

                while (l <= r) {
                    int m = l + ((r - l) >> 1);
                    if (matrix[mid][m] == target) {
                        return true;
                    } else if (matrix[mid][m] > target) {
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                }

                left = mid + 1;
            }
        }

        return false;
    }

}
