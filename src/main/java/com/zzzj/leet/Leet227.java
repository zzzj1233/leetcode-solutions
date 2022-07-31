package com.zzzj.leet;

import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2022-07-30 10:29
 */
public class Leet227 {

    public static void main(String[] args) {
        // "1*2-3/4+5*6-7*8+9/10"
        System.out.println(calculate("42"));
    }

    public static int calculate(String s) {

        int ADD = -1;
        int SUB = -2;

        int MUL = -3;
        int DIV = -4;

        char[] chars = s.toCharArray();

        int N = s.length();

        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            char c = chars[i];
            if (c == ' ') {
                continue;
            }
            // 是数字
            if (Character.isDigit(c)) {
                int digit = Character.digit(c, 10);
                if (stack.isEmpty()) {
                    stack.add(digit);
                } else {
                    // 如果下一个是操作符,或者下一个到底了,那么计算上一个操作符
                    boolean needCalculate = i + 1 == N || !Character.isDigit(chars[i + 1]);

                    int num = digit;

                    while (!stack.isEmpty() && stack.peekLast() > 0) {
                        num = stack.removeLast() * 10 + num;
                    }

                    if (!stack.isEmpty() && needCalculate) {
                        Integer lastOper = stack.peekLast();
                        if (lastOper == MUL) {
                            stack.removeLast();
                            stack.add(stack.removeLast() * num);
                        } else if (lastOper == DIV) {
                            stack.removeLast();
                            stack.add(stack.removeLast() / num);
                        } else {
                            stack.add(num);
                        }
                    } else {
                        stack.add(num);
                    }
                }
            } else {
                // 是运算符
                if (c == '+') {
                    stack.add(ADD);
                } else if (c == '-') {
                    stack.add(SUB);
                } else if (c == '*') {
                    stack.add(MUL);
                } else {
                    stack.add(DIV);
                }
            }
        }


        for (Integer item : stack) {
            if (item >= 0) {
                System.out.print(item);
            } else {
                if (item == ADD) {
                    System.out.print(" + ");
                } else if (item == SUB) {
                    System.out.print(" - ");
                } else if (item == MUL) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" / ");
                }
            }
        }

        System.out.println();

        while (stack.size() > 1) {
            Integer lastNum = stack.removeFirst();

            Integer operate = stack.removeFirst();

            if (operate == ADD) {
                stack.addFirst(lastNum + stack.removeFirst());
            } else if (operate == SUB) {
                stack.addFirst(lastNum - stack.removeFirst());
            }
        }


        return stack.peekLast();
    }


}
