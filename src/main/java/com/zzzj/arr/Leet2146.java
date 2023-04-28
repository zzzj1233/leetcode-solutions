package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2023-04-28 14:55
 */
public class Leet2146 {

    public static void main(String[] args) {

//        System.out.println(highestRankedKItems(
//                LeetUtils.convertInts("[[1,2,0,1],[1,3,0,1],[0,2,5,1]]"),
//                new int[]{2, 5},
//                new int[]{0, 0},
//                3
//        ));
//
//        System.out.println(highestRankedKItems(
//                LeetUtils.convertInts("[[1,2,0,1],[1,3,3,1],[0,2,5,1]]"),
//                new int[]{2, 3},
//                new int[]{2, 3},
//                2
//        ));

        System.out.println(highestRankedKItems(
                LeetUtils.convertInts("[[2,0,2],[4,5,3],[2,0,2]]"),
                new int[]{2, 5},
                new int[]{1, 1},
                9
        ));

    }

    static int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public static List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {

        int low = pricing[0];
        int high = pricing[1];

        PriorityQueue<CompatibleEntity> pq = new PriorityQueue<>();

        int M = grid.length;
        int N = grid[0].length;

        boolean[][] visited = new boolean[M][N];

        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1]});

        int step = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] pos = queue.removeFirst();
                int row = pos[0];
                int col = pos[1];
                visited[row][col] = true;

                int price = grid[row][col];

                if (price != 1 && price >= low && price <= high) {
                    pq.add(new CompatibleEntity(step, price, row, col));
                }

                for (int[] dir : DIRS) {
                    int r = row + dir[0];
                    int c = col + dir[1];

                    // 没走到墙
                    if (r >= 0 && c >= 0 && r < M && c < N && !visited[r][c]
                            && grid[r][c] != 0
                    ) {
                        visited[r][c] = true;
                        queue.addLast(new int[]{r, c});
                    }

                }

            }

            step++;

        }

        int end = Math.min(pq.size(), k);

        List<List<Integer>> ans = new ArrayList<>(end);

        for (int i = 0; i < end; i++) {
            CompatibleEntity rm = pq.remove();
            ans.add(
                    Arrays.asList(rm.row, rm.col)
            );
        }

        return ans;
    }

    static class CompatibleEntity implements Comparable<CompatibleEntity> {

        final int step;

        final int price;

        final int row;

        final int col;

        public CompatibleEntity(int step, int price, int row, int col) {
            this.step = step;
            this.price = price;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(CompatibleEntity other) {
            if (step != other.step) {
                return step - other.step;
            }

            if (price != other.price) {
                return price - other.price;
            }

            if (row != other.row) {
                return row - other.row;
            }

            if (col != other.col) {
                return col - other.col;
            }

            return 0;
        }

    }

}
