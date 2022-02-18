package com.zzzj.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author zzzj
 * @create 2021-12-01 15:25
 */
public class Leet739 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70})));
    }

    /**
     * 输入: temperatures = [89,62,70,58,47,47,46,76,100,70]
     * 输出: [1,1,4,2,1,1,0,0]
     * // 73 75 76
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 0) {
            return new int[]{};
        }

        int[] answer = new int[temperatures.length];

        Stack<Integer> stack = new Stack<>();

        stack.add(temperatures[temperatures.length - 1]);

        for (int i = temperatures.length - 2; i >= 0; i--) {
            stack.add(temperatures[i] > stack.peek() ? temperatures[i] : stack.peek());
        }

        for (int i = 0; i < temperatures.length; i++) {
            Integer pop = stack.pop();
            if (temperatures[i] >= pop) {
                answer[i] = 0;
            } else {
                for (int j = i + 1; j < temperatures.length; j++) {
                    if (temperatures[j] > temperatures[i]) {
                        answer[i] = j - i;
                        break;
                    }
                }
            }
        }

        return answer;
    }

}
