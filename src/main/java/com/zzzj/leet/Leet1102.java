package com.zzzj.leet;

public class Leet1102 {

    public static void main(String[] args) {
        System.out.println(maximumMinimumPath(LeetUtils.convertInts("[[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]")));
    }

    public static int maximumMinimumPath(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Info[][] infos = new Info[rows][cols];

        infos[rows - 1][cols - 1] = Info.right(grid[rows - 1][cols - 1]);

        // 最后一行
        for (int i = cols - 2; i >= 0; i--) {
            infos[rows - 1][i] = Info.right(Math.min(grid[rows - 1][i], infos[rows - 1][i + 1].right));
        }

        for (int i = rows - 2; i >= 0; i--) {

            infos[i][cols - 1] = Info.right(Math.min(grid[i][cols - 1], infos[i + 1][cols - 1].max()));

            for (int j = cols - 1; j >= 0; j--) {
                Info info = new Info();

                if (j < cols - 1) {
                    info.right = j + 1 < cols ? Math.min(grid[i][j], infos[i][j + 1].max()) : Integer.MIN_VALUE;
                }

                info.bottom = Math.min(grid[i][j], infos[i + 1][j].max());

                infos[i][j] = info;
            }

            infos[i][0].left = Math.min(grid[i][0], infos[i + 1][0].max());

            for (int j = 1; j < cols - 1; j++) {
                infos[i][j].left = Math.min(grid[i][j], infos[i][j - 1].max());
            }

        }


        return -1;
    }

    private static class Info {
        int left = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        int bottom = Integer.MIN_VALUE;

        public int max() {
            return Math.max(bottom, Math.max(left, right));
        }

        public static Info left(int val) {
            Info info = new Info();
            info.left = val;
            return info;
        }

        public static Info right(int val) {
            Info info = new Info();
            info.right = val;
            return info;
        }

        public static Info bottom(int val) {
            Info info = new Info();
            info.bottom = val;
            return info;
        }

        @Override
        public String toString() {
            return "[ left = " + left + " , right = " + right + " , bottom = " + bottom + " ]";
        }
    }


}
