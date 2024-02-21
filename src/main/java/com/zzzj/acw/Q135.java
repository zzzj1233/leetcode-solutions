package com.zzzj.acw;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author zzzj
 * @create 2024-01-31 11:17
 */
public class Q135 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner("20 1\n" +
                "24 -11 -16 2 2 -13 7 15 -19 21 -12 -14 9 -17 14 -10 -18 22 4 11 ");

        int N = scanner.nextInt();

        int M = scanner.nextInt();

        long[] ps = new long[N + 1];

        for (int i = 1; i <= N; i++)
            ps[i] = ps[i - 1] + scanner.nextInt();

        LinkedList<Integer> stack = new LinkedList<>();

        long ans = Long.MIN_VALUE;

        stack.add(0);

        for (int i = 1; i <= N; i++) {

            int leftLimit = Math.max(0, i - M);

            if (!stack.isEmpty() && stack.peekFirst() < leftLimit)
                stack.removeFirst();

            ans = Math.max(ans, ps[i] - ps[stack.peekFirst()]);

            while (!stack.isEmpty() && ps[stack.peekLast()] >= ps[i])
                stack.removeLast();

            stack.add(i);

        }


        System.out.println(ans);
    }

}
