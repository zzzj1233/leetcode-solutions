package com.zzzj.stack;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-12-13 11:38
 */
public class Leet1526 {

    public static void main(String[] args) {
//        System.out.println(minNumberOperations(new int[]{3, 1, 5, 4, 2}));
        System.out.println(minNumberOperations(new int[]{5, 3, 1, 4, 6}));
    }

    public static int minNumberOperations(int[] target) {

        int N = target.length;

        LinkedList<Integer> stack = new LinkedList<>();

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int num = target[i];
            int count = num;
            while (!stack.isEmpty() && num < stack.peekLast()) {
                Integer last = stack.removeLast();
                ans += Math.max(0, last - count);
                ans += Math.max(0, last - count);
                count = last;
            }
            stack.add(num);
        }

        int del = 0;

        while (!stack.isEmpty()) {
            Integer first = stack.removeFirst();
            ans += first - del;
            del += first - del;
        }

        return ans;
    }

}
