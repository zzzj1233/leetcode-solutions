package com.zzzj.leet;

import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2022-05-22 20:32
 */
public class Leet739 {

    public static int[] dailyTemperatures(int[] temperatures) {

        int N = temperatures.length;

        int[] ans = new int[N];

        LinkedList<Integer> stack = new LinkedList<>();

        stack.add(0);

        for (int i = 1; i < N; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peekLast()]) {
                ans[stack.peekLast()] = i - stack.removeLast();
            }
            stack.add(i);
        }

        while (!stack.isEmpty()){
            ans[stack.removeLast()] = 0;
        }

        return ans;
    }

}
