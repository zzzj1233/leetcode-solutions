package com.zzzj.hot;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-04-24 12:00
 */
public class Leet279 {

    public static void main(String[] args) {
        System.out.println(numSquares(12));
        System.out.println(numSquares(5124));
    }

    public static int numSquares(int n) {
        return bfs(n);
    }

    public static int bfs(int n) {
        LinkedList<Integer> queue = new LinkedList<>();

        queue.addLast(n);

        int level = 0;

        Set<Integer> visited = new HashSet<>(32);

        while (!queue.isEmpty()) {

            int size = queue.size();

            level++;

            for (int i = 0; i < size; i++) {

                Integer first = queue.removeFirst();

                int sqrt = (int) Math.sqrt(first);

                for (int j = 1; j <= sqrt; j++) {
                    int sub = first - j * j;
                    if (sub == 0) {
                        return level;
                    }
                    if (visited.contains(sub)) {
                        continue;
                    }
                    visited.add(sub);
                    queue.addLast(sub);
                }

            }

        }

        return -1;
    }

}
