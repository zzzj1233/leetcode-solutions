package com.zzzj.stack;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-12-13 11:38
 */
public class Leet1526 {

    public static void main(String[] args) {
        System.out.println(minNumberOperations(new int[]{3, 1, 5, 4, 2}));
    }

    public static int minNumberOperations(int[] target) {
        int answer = 0;
        int curVal = 1;

        LinkedList<Integer> stack = new LinkedList<>();

        int max = 0;

        for (int i = 0; i < target.length; i++) {
            stack.add(i);
            max = Math.max(max, target[i]);
        }

        while (true) {
            Iterator<Integer> iterator = stack.iterator();

            while (iterator.hasNext()) {
                Integer index = iterator.next();
                if (target[index] < curVal && iterator.hasNext()) {
                    answer++;
                }
            }

            curVal++;
            answer++;

            if (curVal > max) {
                break;
            }

        }

        return answer;
    }

}
