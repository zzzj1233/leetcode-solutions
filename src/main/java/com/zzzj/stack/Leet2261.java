package com.zzzj.stack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-12-06 10:58
 */
public class Leet2261 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }

    /**
     * 请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替
     * <p>
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];

        LinkedList<Integer> singleStack = new LinkedList<>();

        singleStack.add(0);

        for (int i = 1; i < temperatures.length; i++) {
            int num = temperatures[i];

            while (!singleStack.isEmpty() && num > temperatures[singleStack.peekLast()]) {
                // remove and add answer
                Integer last = singleStack.removeLast();
                answer[last] = i - last;
            }

            singleStack.addLast(i);
        }

        return answer;
    }

}
