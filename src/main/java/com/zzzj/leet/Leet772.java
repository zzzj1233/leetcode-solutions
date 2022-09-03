package com.zzzj.leet;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-09-01 14:43
 */
public class Leet772 {

    public static void main(String[] args) {
        System.out.println(calculate("2*(5+5*2)/3+(6/2+8)"));
        System.out.println(calculate("6-4/2"));
        System.out.println(calculate("1+1"));

        // 2 - 0 + 30 - 56 + 0 = 56 - 32 = -24
        System.out.println(calculate("1*2-3/4+5*6-7*8+9/10"));
    }

    public static int calculate(String s) {
        return dfs(s, 0)[0];
    }

    static final Integer MUL = new Integer("999");
    static final Integer DIV = new Integer("998");
    static final Integer ADD = new Integer("997");
    static final Integer SUB = new Integer("996");

    public static void calculate(LinkedList<Integer> stack, int num) {
        if (stack.isEmpty() || (stack.peekLast() == ADD || stack.peekLast() == SUB)) {
            stack.addLast(num);
        } else if (!stack.isEmpty()) {
            Integer last = stack.removeLast();
            int leftNum = stack.removeLast();
            if (last == MUL) {
                stack.addLast(leftNum * num);
            } else {
                stack.addLast(leftNum / num);
            }
        }
    }

    public static int[] dfs(String str, int index) {

        LinkedList<Integer> stack = new LinkedList<>();

        while (index < str.length() && str.charAt(index) != ')') {
            char c = str.charAt(index);
            if (c == '(') {
                int[] ints = dfs(str, index + 1);
                calculate(stack, ints[0]);
                index = ints[1];
            } else if (Character.isDigit(c)) {
                int num = 0;
                while (index < str.length() && Character.isDigit(str.charAt(index))) {
                    num = num * 10 + Character.digit(str.charAt(index), 10);
                    index++;
                }
                calculate(stack, num);
            } else {
                if (c == '+') {
                    stack.addLast(ADD);
                } else if (c == '-') {
                    stack.addLast(SUB);
                } else if (c == '*') {
                    stack.addLast(MUL);
                } else {
                    stack.addLast(DIV);
                }
                index++;
            }
        }

        while (stack.size() >= 3) {
            int left = stack.removeFirst();
            Integer op = stack.removeFirst();
            int right = stack.removeFirst();
            if (op == ADD) {
                stack.addFirst(left + right);
            } else {
                stack.addFirst(left - right);
            }
        }

        return new int[]{stack.removeFirst(), index + 1};
    }

}
