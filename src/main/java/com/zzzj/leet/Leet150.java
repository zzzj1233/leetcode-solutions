package com.zzzj.leet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-09-26 15:27
 */
public class Leet150 {

    /**
     * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     * 输出：22
     * 解释：
     * 该算式转化为常见的中缀算术表达式为：
     * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     * <p>
     */
    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }

    private static String ADD = "+";
    private static String SUB = "-";
    private static String MUL = "*";
    private static String DIV = "/";

    private static final List<String> operators = Arrays.asList(ADD, SUB, MUL, DIV);

    public static int evalRPN(String[] tokens) {

        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < tokens.length; i++) {
            if (operators.contains(tokens[i])) {
                Integer y = stack.removeLast();
                Integer x = stack.removeLast();

                if (ADD.equals(tokens[i])) {
                    stack.addLast(x + y);
                } else if (SUB.equals(tokens[i])) {
                    stack.addLast(x - y);
                } else if (MUL.equals(tokens[i])) {
                    stack.addLast(x * y);
                } else {
                    stack.addLast(x / y);
                }
            } else {
                stack.addLast(Integer.parseInt(tokens[i]));
            }
        }

        return stack.removeLast();
    }

}
