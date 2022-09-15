package com.zzzj.leet;


import java.util.*;

/**
 * @author zzzj
 * @create 2022-09-09 15:01
 */
public class Leet1942 {

    public static void main(String[] args) {

//        System.out.println(smallestChair(LeetUtils.convertInts("[[9, 17], [7, 15], [2, 5], [4, 5]]"), 0));
//
//        System.exit(0);

        Solution solution = new Solution();

        for (int i = 0; i < 1000; i++) {
            int N = 1000;
            int M = 1000;

            int[][] times = new int[N][2];
            Set<Integer> used = new HashSet<>();
            for (int j = 0; j < N; j++) {
                times[j][0] = LeetUtils.random.nextInt(M) + 1;
                while (used.contains(times[j][0])) {
                    times[j][0] = LeetUtils.random.nextInt(M) + 1;
                }

                used.add(times[j][0]);

                times[j][1] = times[j][0] + LeetUtils.random.nextInt(M) + 1;
            }

            int k = LeetUtils.random.nextInt(N);

            if (smallestChair(times, k) != solution.smallestChair(times, k)) {
                System.out.println("Error");
                System.out.println(Arrays.deepToString(times));
                System.out.println(k);
                System.out.println(smallestChair(times, k));
                System.out.println(solution.smallestChair(times, k));

                int[][] helper = new int[N][3];

                for (int j = 0; j < times.length; j++) {
                    helper[j][0] = times[j][0];
                    helper[j][1] = times[j][1];
                    helper[j][2] = j;
                }

                Arrays.sort(helper, Comparator.comparingInt(o -> o[0]));

                System.out.println(Arrays.deepToString(helper));

                return;
            }
        }
        System.out.println("Ok");
    }

    public static int smallestChair(int[][] times, int targetFriend) {
        int N = times.length;

        PriorityQueue<int[]> arrive = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        PriorityQueue<int[]> leave = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        for (int i = 0; i < N; i++) {
            arrive.add(new int[]{times[i][0], times[i][1], i});
        }

        TreeSet<Integer> set = new TreeSet<>();

        set.add(0);

        while (true) {
            int[] item = arrive.remove();
            int arriveTime = item[0];
            int levelTime = item[1];
            int index = item[2];

            // 我来的时候这个人已经要离开了
            while (!leave.isEmpty() && leave.peek()[0] <= arriveTime) {
                set.add(leave.remove()[1]);
            }

            int curChair = set.pollFirst();

            if (set.isEmpty()) {
                set.add(curChair + 1);
            }

            leave.add(new int[]{levelTime, curChair});

            if (index == targetFriend) {
                return curChair;
            }

        }

    }

    static class Solution {
        public int smallestChair(int[][] times, int targetFriend) {
            int len = times.length;
            int[][] t = new int[len][3];
            for (int i = 0; i < len; i++) {
                t[i][0] = times[i][0];
                t[i][1] = times[i][1];
                t[i][2] = i;
            }
            Arrays.sort(t, (a, b) -> a[0] - b[0]);
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            TreeSet<Integer> ts = new TreeSet<>();
            ts.add(0);
            for (int i = 0; i < len; i++) {
                int[] cur = t[i];
                int arrivali = cur[0], leavingi = cur[1], index = cur[2];
                while (!queue.isEmpty() && queue.peek()[0] <= arrivali) {
                    int[] leave = queue.poll();
                    ts.add(leave[1]);
                }
                int low = ts.first();
                if (index == targetFriend) {
                    return low;
                }
                ts.remove(low);
                if (ts.isEmpty()) {
                    ts.add(low + 1);
                }
                queue.add(new int[]{leavingi, low});
            }
            return -1;
        }
    }

}
