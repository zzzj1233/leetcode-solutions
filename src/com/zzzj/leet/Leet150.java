package com.zzzj.leet;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
        System.out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    private static String ADD = "+";
    private static String SUB = "-";
    private static String MUL = "*";
    private static String DIV = "/";

    private static final List<String> operators = Arrays.asList(ADD, SUB, MUL, DIV);

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (!operators.contains(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                // 取出两个数
                Integer num2 = stack.pop();
                Integer num1 = stack.pop();

                if (ADD.equals(token)) {
                    stack.push(num1 + num2);
                } else if (SUB.equals(token)) {
                    stack.push(num1 - num2);
                } else if (MUL.equals(token)) {
                    stack.push(num1 * num2);
                } else {
                    stack.push(num1 / num2);
                }
            }
        }

        return stack.pop();
    }

}
