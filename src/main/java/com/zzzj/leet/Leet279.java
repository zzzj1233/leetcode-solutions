package com.zzzj.leet;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author zzzj
 * @create 2021-10-20 17:37
 */
public class Leet279 {

    public static void main(String[] args) {
        System.out.println(numSquares(13));
        System.out.println(dynamicPlanning(13));
    }

    // 12 = 4 + 4 + 4
    // 13 = 9 + 4
    public static int numSquares(int n) {
        return bfs(n);
    }

    private static int dynamicPlanning(int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j * j <= i; j++) {
                int sub = i - (j * j);
                dp[j] = dp[sub] + 1;
            }

        }

        return dp[n];
    }

    private static int bfs(int n) {
        LinkedList<Integer> queue = new LinkedList<>();

        Set<Integer> visited = new HashSet<>();

        queue.add(n);

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            level++;

            for (int i = 0; i < size; i++) {
                Integer last = queue.removeFirst();

                for (int j = 1; j * j <= last; j++) {
                    int sub = last - (j * j);
                    if (sub == 0) {
                        return level;
                    }
                    if (visited.contains(sub)) {
                        continue;
                    }
                    visited.add(sub);
                    queue.add(sub);
                }
            }

        }

        return -1;
    }

}
