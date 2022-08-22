package com.zzzj.union;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Zzzj
 * @create 2021-11-14 14:29
 */
public class Leet547 {


    public static void main(String[] args) {
        System.out.println(findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
    }

    public static int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;

        TreeSet<Integer> visited = new TreeSet<>();

        for (int i = 1; i <= N; i++) {
            visited.add(i);
        }

        int ans = 0;

        while (!visited.isEmpty()) {
            dfs(visited, isConnected, visited.pollFirst());
            ans++;
        }

        return ans;
    }

    public static void dfs(TreeSet<Integer> visited, int[][] isConnected, int start) {
        int[] others = isConnected[start - 1];

        visited.remove(start);

        for (int i = 0; i < others.length; i++) {
            if (others[i] == 1 && visited.contains(i + 1)) {
                dfs(visited, isConnected, i + 1);
            }
        }
    }

}
