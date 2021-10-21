package com.zzzj.leet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author zzzj
 * @create 2021-10-21 16:08
 */
public class Leet66 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{4,3,2,1})));
    }

    public static int[] plusOne(int[] digits) {
        Stack<Integer> stack = new Stack<>();

        for (int digit : digits) {
            stack.push(digit);
        }

        LinkedList<Integer> list = new LinkedList<>();

        int add = 1;
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            if (pop + add >= 10) {
                int sub = pop + add - 10;
                list.addFirst(sub);
                add = 1;
            } else {
                list.addFirst(pop + add);
                add = 0;
            }
        }

        if (add == 1) {
            list.addFirst(1);
        }

        int[] res = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }

}
