package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-06 17:08
 */
public class Leet286 {

    static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void spread(int[][] rooms, int i, int j, int expect) {
        for (int[] dir : dirs) {
            if (i + dir[0] < rooms.length && i + dir[0] >= 0 && rooms[i + dir[0]][j] > expect) {
                rooms[i + dir[0]][j] = expect;
                // 每个infinity向外扩展
                spread(rooms, i + dir[0], j, expect + 1);
            }
            if (j + dir[1] < rooms[i].length && j + dir[1] >= 0 && rooms[i][j + dir[1]] > expect) {
                rooms[i][j + dir[1]] = expect;
                // 每个infinity向外扩展
                spread(rooms, i, j + dir[1], expect + 1);
            }
        }
    }

    public static void wallsAndGates(int[][] rooms) {

        final int infinity = 2147483647;

        final int door = 0;

        for (int i = 0; i < rooms.length; i++) {

            for (int j = 0; j < rooms[i].length; j++) {

                if (rooms[i][j] == door) {
                    spread(rooms, i, j, 1);
                }

            }

        }


    }

}
