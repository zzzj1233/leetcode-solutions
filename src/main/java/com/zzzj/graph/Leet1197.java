package com.zzzj.graph;

import java.util.*;

/**
 * @author zzzj
 * @create 2023-01-03 17:50
 */
public class Leet1197 {

    public static void main(String[] args) {
        System.out.println(minKnightMoves(2, 1));
    }

    static int[][] DIRS = {
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {-2, -1},
            {-1, -2},
            {1, -2},
            {2, -1},
    };

    public static int minKnightMoves(int x, int y) {

        LinkedList<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0});

        Set<String> visited = new HashSet<>();

        visited.add("0-0");

        int ans = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                int[] item = queue.removeFirst();

                int x1 = item[0];
                int y1 = item[1];

                if (x1 == x && y1 == y) {
                    return ans;
                }

                for (int[] dir : DIRS) {
                    int x2 = x1 + dir[0];
                    int y2 = y1 + dir[1];

                    String key = x2 + "-" + y2;

                    if (visited.add(key)){
                        queue.addLast(new int[]{x2, y2});
                    }

                }

            }

            ans++;
        }

        return ans;
    }

}
