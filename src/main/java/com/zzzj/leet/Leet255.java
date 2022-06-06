package com.zzzj.leet;

import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2022-05-29 19:57
 */
public class Leet255 {

    public static void main(String[] args) {
//        System.out.println(verifyPreorder(new int[]{5, 2, 1, 3, 6}));
        System.out.println(verifyPreorder(new int[]{5, 2, 1, 3, 1, 6}));
//        System.out.println(verifyPreorder(new int[]{5, 2, 6, 1, 6}));
//        System.out.println(verifyPreorder(new int[]{1, 3, 4, 2}));
    }


    // [1,3,4,2]
    public static boolean verifyPreorder(int[] order) {
        LinkedList<Integer> stack = new LinkedList<>();

        stack.add(order[0]);

        int min = Integer.MIN_VALUE;

        for (int i = 1; i < order.length; i++) {

            if (order[i] < min) {
                return false;
            }

            if (order[i] > order[i - 1]) {
                while (!stack.isEmpty() && stack.peekLast() < order[i]) {
                    min = stack.removeLast();
                }
            }

            stack.add(order[i]);
        }


        return true;
    }

    public static boolean right(int[] preorder) {
        int len = preorder.length;
        int[] stack = new int[len];
        int top = -1;
        int min = Integer.MIN_VALUE;

        for (int value : preorder) {
            if (value < min) {
                return false;
            }

            while (top > -1 && value > stack[top]) {
                min = stack[top];
                top--;
            }

            stack[++top] = value;
        }

        return true;
    }


}
