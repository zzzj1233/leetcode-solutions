package com.zzzj.hot;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-04-22 16:32
 */
public class Leet240 {

    public static void main(String[] args) {
        System.out.println(searchMatrix(LeetUtils.convertInts("[[-1,-1]]"), 3));
    }

    // 在二维矩阵中搜索数
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;

        // 过滤掉 > target的列
        int i = 0;
        int j = col - 1;

        int mid = (j + i) / 2;

        while (i <= j && (matrix[0][mid] > target || matrix[row - 1][mid] < target)) {
            if (matrix[0][mid] > target) {
                j = mid - 1;
            } else if (matrix[row - 1][mid] < target) {
                i = mid + 1;
            }
            mid = (j + i) / 2;
        }

        // 确定好了 i ~ j 就是目标列

        while (i <= j) {
            int l = 0;
            int r = row - 1;

            while (l <= r) {
                mid = (l + r) / 2;
                int val = matrix[mid][i];
                if (val == target) {
                    return true;
                } else if (val > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            i++;
        }

        return false;
    }

    public static boolean right(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int searchRow = 0;

        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == target) {
                return true;
            } else if (matrix[i][0] > target) {
                searchRow = i == 0 ? 0 : i - 1;
                break;
            } else {
                searchRow = i;
            }
        }

        for (int i = 0; i < col; i++) {
            if (matrix[searchRow][i] == target) {
                return true;
            }
            if (matrix[searchRow][i] > target) {
                for (int j = searchRow - 1; j >= 0; j--) {
                    if (matrix[j][i] == target) {
                        return true;
                    }
                    if (matrix[j][i] < target) {
                        break;
                    }
                }
            }
        }

        return false;
    }

}
