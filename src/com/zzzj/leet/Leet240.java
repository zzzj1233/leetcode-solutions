package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-25 09:56
 */
public class Leet240 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4},
                {2, 5},
        };

        // 那么我们可以从根（右上角）开始搜索，如果当前的节点不等于目标值，可以按照树的搜索顺序进行：

        // 当前节点「大于」目标值，搜索当前节点的「左子树」，也就是当前矩阵位置的「左方格子」，即 cc--

        // 当前节点「小于」目标值，搜索当前节点的「右子树」，也就是当前矩阵位置的「下方格子」，即 rr++

        System.out.println(searchMatrix(matrix, 5));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
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
