package com.zzzj.leet;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2024-02-27 17:20
 */
public class Leet1643 {

    public static void main(String[] args) {
        System.out.println(kthSmallestPath(new int[]{2, 3}, 1));

    }

    private static class Location {
        final int x;
        final int y;
        final String path;

        private Location(int x, int y, String path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }
    }

    public static String kthSmallestPath(int[] destination, int k) {

        PriorityQueue<String> pq = new PriorityQueue<>(k, Comparator.reverseOrder());

        LinkedList<Location> queue = new LinkedList<>();

        queue.addLast(new Location(0, 0, ""));

        int dr = destination[0];

        int dc = destination[1];

        while (!queue.isEmpty()) {

            Location node = queue.removeFirst();

            if (node.x == dr && node.y == dc) {

                if (pq.size() < k) {
                    pq.add(node.path);
                } else if (pq.peek().compareTo(node.path) > 0) {
                    pq.remove();
                    pq.add(node.path);
                }
                continue;
            }

            if (node.x + 1 <= dr) {
                queue.addLast(new Location(node.x + 1, node.y, node.path + "V"));
            }

            if (node.y + 1 <= dc) {
                queue.addLast(new Location(node.x, node.y + 1, node.path + "H"));
            }

        }

        return pq.peek();
    }

}
