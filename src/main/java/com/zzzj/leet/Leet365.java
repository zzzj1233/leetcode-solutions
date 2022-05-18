package com.zzzj.leet;

import javax.swing.plaf.ViewportUI;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-05-12 17:13
 */
public class Leet365 {

    public static boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        return bfs(jug1Capacity, jug2Capacity, targetCapacity);
    }

    static class State {
        int x;
        int y;

        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    // 1. 装满一个水壶
    // 2. 清空一个水壶
    // 3. 从一个水壶向另外一个水壶倒水，直到装满或者倒空
    public static boolean bfs(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        LinkedList<State> queue = new LinkedList<>();

        queue.add(new State(0, 0));

        HashSet<Long> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            State first = queue.removeFirst();
            if (visited.contains(hash(first))) {
                continue;
            }
            if (first.x == targetCapacity || first.y == targetCapacity || first.x + first.y == targetCapacity) {
                return true;
            }
            visited.add(hash(first));
            queue.add(new State(jug1Capacity, first.y));
            queue.add(new State(first.x, jug2Capacity));
            queue.add(new State(first.x, 0));
            queue.add(new State(0, first.y));

            // 右边的倒进左边

            queue.add(new State(Math.min(first.x + first.y, jug1Capacity), Math.max(jug1Capacity - first.x, 0)));
            // 左边的倒进右边
            queue.add(new State(Math.max(0, jug2Capacity - first.y), Math.min(first.y + first.x, jug2Capacity)));
        }

        return false;
    }

    public static long hash(State state) {
        return (long) state.x * 1000001 + state.y;
    }

}
