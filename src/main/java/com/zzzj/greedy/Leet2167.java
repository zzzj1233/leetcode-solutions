package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2024-01-10 17:48
 */
public class Leet2167 {

    public static void main(String[] args) {

        System.out.println(minimumTime("11111"));

//        System.exit(0);

        for (int i = 0; i < 10000; i++) {

            int M = 5000;

            String s = LeetUtils.randomString(M, "01");

            int r = minimumTime(s);

            int rr = right(s);

            if (r != rr) {
                System.out.println("Error");
                System.out.println("s = " + s);
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                return;
            }

        }

        System.out.println("Ok");
    }

    public static int minimumTime(String s) {

        int N = s.length();

        int[] left = new int[N];

        int rightest = -1;

        for (int i = 0; i < N; i++) {
            left[i] = rightest;
            if (s.charAt(i) == '1')
                rightest = i;
        }

        if (rightest == -1)
            return 0;

        int[] right = new int[N];

        int leftest = N;

        for (int i = N - 1; i >= 0; i--) {
            right[i] = leftest;
            if (s.charAt(i) == '1')
                leftest = i;
        }

        if (leftest == rightest)
            return leftest == 0 || rightest == N - 1 ? 1 : 2;

        int[] cost = new int[N];

        PriorityQueue<Integer> queue = new PriorityQueue<>(N, Comparator.comparingInt(o -> cost[o]));

        int cnt = 0;

        for (int i = leftest; i <= rightest; i++) {
            if (s.charAt(i) == '1') {
                cnt++;
                cost[i] = (cnt << 1) + (N - right[i]);
                queue.add(i);
            }
        }

        int ans = Math.min(
                Math.min(
                        rightest + 1,
                        N - leftest
                ),
                Math.min(
                        cost[queue.peek()],
                        cnt << 1
                )
        );

        int removeCnt = 0;

        int offset = 0;

        for (int i = 0; i < N; i++) {

            while (!queue.isEmpty() && queue.peek() < i)
                queue.remove();

            if (s.charAt(i) != '1') {
                ans = Math.min(ans, (left[i] + 1) + (N - right[i]));
                continue;
            }

            if (queue.isEmpty())
                continue;

            offset = (left[i] + 1) - (removeCnt << 1);

            ans = Math.min(ans, cost[queue.peek()] + offset);

            removeCnt++;
        }

        return ans;
    }

    public static int right(String s) {
        int len = s.length(), ones = -1, num, ans = 0;
        for (int index = 0; index < len; index++) {
            num = s.charAt(index) - '0';
            ones = Math.min(index, ones + (num << 1));
            if (ones - index < ans) ans = ones - index;
        }
        return ans + len;
    }
}
