package com.zzzj.hot;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-04-19 17:51
 */
public class Leet134 {


    public static void main(String[] args) {

        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));

        System.out.println(canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));

        System.out.println(canCompleteCircuit(new int[]{5, 1, 2, 3, 4}, new int[]{4, 4, 1, 5, 1}));

    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int N = gas.length;

        int M = N << 1;

        int[] w = new int[M];

        for (int i = 0; i < N; i++)
            w[i] = w[i + N] = gas[i] - cost[i];

        long[] s = new long[M + 1];

        for (int i = 1; i <= M; i++)
            s[i] = s[i - 1] + w[i - 1];

        LinkedList<Integer> minStack = new LinkedList<>();

        for (int i = 1; i < N; i++) {

            while (!minStack.isEmpty() && s[minStack.peekLast()] >= s[i])
                minStack.removeLast();

            minStack.add(i);
        }

        // 顺时针: [ i - ( i + N ) ]
        for (int i = 1; i <= N; i++) {

            if (!minStack.isEmpty() && minStack.peekFirst() < i)
                minStack.removeFirst();

            while (!minStack.isEmpty() && s[minStack.peekLast()] >= s[i + N])
                minStack.removeLast();

            minStack.add(i + N);

            if (s[minStack.peekFirst()] - s[i - 1] >= 0)
                return i - 1;

        }

        return -1;
    }


}
