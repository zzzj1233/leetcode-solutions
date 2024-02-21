package com.zzzj.dp;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-02-23 16:11
 */
public class Leet1900 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(earliestAndLatest(4, 2, 3)));

        System.out.println(Arrays.toString(earliestAndLatest(3, 2, 3)));

        System.out.println(Arrays.toString(earliestAndLatest(11, 2, 4)));

        System.out.println(Arrays.toString(earliestAndLatest(5, 1, 5)));

    }

    public static int rightest(int right, int x) {

        for (int i = right - 1; i >= 0; i--) {
            if ((x & (1 << i)) != 0)
                return i;
        }

        return -1;
    }

    public static int leftest(int left, int x) {

        for (int i = left + 1; i <= 30; i++) {
            if ((x & (1 << i)) != 0)
                return i;
        }

        return -1;
    }

    static int required;

    public static boolean check(int s, int first, int second) {

        int lc = 0;
        int rc = 0;

        for (int i = first - 1; i >= 0; i--) {
            if ((s & (1 << i)) != 0)
                lc++;
        }

        for (int i = second + 1; i <= 30; i++) {
            if ((s & (1 << i)) != 0)
                rc++;
        }

        return lc == rc;
    }

    public static int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {

        Deque<int[]> queue = new LinkedList<>();

        queue.addLast(new int[]{(1 << n) - 1, 1});

        int min = Integer.MAX_VALUE;

        int max = Integer.MIN_VALUE;

        required = (1 << (firstPlayer - 1)) | (1 << (secondPlayer - 1));

        Set<Integer> vis = new HashSet<>();

        while (!queue.isEmpty()) {

            int[] rm = queue.removeFirst();

            int s = rm[0];
            int times = rm[1];

            int c = Integer.bitCount(s);

            if (c <= 1)
                continue;

            if (check(s, firstPlayer - 1, secondPlayer - 1)) {
                min = Math.min(min, times);
                max = Math.max(max, times);
                continue;
            }

            // 计算下一个状态
            addStatus(s, leftest(-1, s), rightest(31, s), vis, times + 1, queue, c / 2);
        }

        return new int[]{min, max};
    }

    public static void addStatus(int s, int left, int right, Set<Integer> vis, int times, Deque<int[]> queue, int c) {
        if ((s & required) != required)
            return;

        if (c == 0) {
            if (vis.add(s))
                queue.addLast(new int[]{s, times});
            return;
        }

        int nextL = leftest(left, s);

        int nextR = rightest(right, s);

        addStatus(s ^ (1 << left), nextL, nextR, vis, times, queue, c - 1);

        addStatus(s ^ (1 << right), nextL, nextR, vis, times, queue, c - 1);
    }


}
