package com.zzzj.graph;

import java.util.List;

/**
 * @author zzzj
 * @create 2022-08-19 18:00
 */
public class Leet841 {

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms.isEmpty()) {
            return true;
        }

        int N = rooms.size();

        boolean[] visited = new boolean[N];

        dfs(visited, rooms, 0);

        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }

        return true;
    }

    public static void dfs(boolean[] visited, List<List<Integer>> rooms, int index) {
        visited[index] = true;

        List<Integer> list = rooms.get(index);

        for (Integer item : list) {
            if (!visited[item]) {
                dfs(visited, rooms, item);
            }
        }
    }

}
