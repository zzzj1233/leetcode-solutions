package com.zzzj.singlestack;

import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2021-12-12 10:18
 */
public class Leet739 {


    public static int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];

        LinkedList<Integer> stack = new LinkedList<>();

        stack.add(0);

        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > stack.peekLast()) {
                Integer last = stack.removeLast();
                answer[last] = i - last;
            }
            stack.add(i);
        }


        return answer;
    }

}
