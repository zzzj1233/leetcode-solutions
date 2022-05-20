package com.zzzj.leet;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2022-05-18 15:20
 */
public class Leet436 {

    public static void main(String[] args) {
        findRightInterval(LeetUtils.convertInts("[[3,4],[2,3],[1,2]]"));
    }

    private static class Node {
        int index;
        int start;

        public Node(int index, int start) {
            this.index = index;
            this.start = start;
        }
    }

    public static int[] findRightInterval(int[][] intervals) {
        // 先排序,再二分
        int N = intervals.length;
        Node[] node = new Node[N];

        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            node[i] = new Node(i, interval[0]);
        }

        Arrays.sort(node, Comparator.comparingInt(o -> o.start));

        int[] ans = new int[N];

        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            ans[i] = binSearch(node, interval[1]);
        }

        return ans;
    }


    public static int binSearch(Node[] node, int end) {
        // 二分找,左到不能再左为止
        int j = node.length - 1;
        int i = 0;
        int mid = i + ((j - i) >> 1);

        int result = -1;

        while (i <= j) {
            if (node[mid].start >= end) {
                result = node[mid].index;
                j = mid - 1;
            } else {
                i = mid + 1;
            }
            mid = i + ((j - i) >> 1);
        }

        return result;
    }

}
