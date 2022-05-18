package com.zzzj.leet;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-05-17 16:56
 */
public class Leet402 {

    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3));
        System.out.println(removeKdigits("10200", 1));
        System.out.println(removeKdigits("142191", 2));
        System.out.println(removeKdigits("9", 1));
        System.out.println(removeKdigits("10", 2));
    }

    // 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字
    public static String removeKdigits(String num, int k) {
        // 1432219
        // k = 3
        // ans = 1219

        // 10200
        // 1
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            int digit = Character.digit(c, 10);
            while (!stack.isEmpty() && digit < stack.peekLast() && k > 0) {
                stack.removeLast();
                k--;
            }
            if (digit == 0 && stack.isEmpty()) {
                continue;
            }
            stack.add(digit);
        }

        while (!stack.isEmpty() && k > 0) {
            stack.removeLast();
            k--;
        }

        StringBuilder builder = new StringBuilder(stack.size());

        stack.forEach(builder::append);

        return builder.length() == 0 ? "0" : builder.toString();
    }

}
