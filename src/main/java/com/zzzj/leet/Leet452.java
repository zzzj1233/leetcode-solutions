package com.zzzj.leet;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 */
public class Leet452 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            final Node node = randomData(10);
            final int[][] arr = node.arr;
            final int[][] copy = node.copy;
            if (findMinArrowShots(arr) != right(copy)) {
                System.out.println("Error");
                return;
            }
        }
    }

    private static class Node {
        int[][] arr;
        int[][] copy;

        public Node(int[][] arr, int[][] copy) {
            this.arr = arr;
            this.copy = copy;
        }
    }

    private static Node randomData(int len) {
        int[][] result = new int[len][];
        int[][] copy = new int[len][];

        for (int i = 0; i < len; i++) {
            int start = LeetUtils.random.nextInt(1000);
            int end = LeetUtils.random.nextInt(1000) + start;
            result[i] = new int[]{start, end};
            copy[i] = new int[]{start, end};
        }

        return new Node(result, copy);
    }

    // [[1,2],[4,5],[1,5]]
    // [[9,12],[1,10],[4,11],[8,12],[3,9],[6,9],[6,7]]
    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[0], o2[0]);
        });

        // output : 1

        // [[1, 10], [3, 9], [4, 11], [6, 7], [6, 9], [8, 12], [9, 12]]

        int start = points[0][0];
        int end = points[0][1];

        int ans = 1;

        for (int i = 1; i < points.length; i++) {
            // start
            int curStart = points[i][0];
            int curEnd = points[i][1];

            if (curStart <= end) {
                end = Math.min(end, curEnd);
            } else {
                end = curEnd;
                ans++;
            }

        }

        return ans;
    }

    public static int right(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                if (point1[1] > point2[1]) {
                    return 1;
                } else if (point1[1] < point2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon : points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;
    }


}