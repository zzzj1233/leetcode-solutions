package com.zzzj.leet;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-07-30 11:53
 */
public class Leet241 {

    public static void main(String[] args) {
//        System.out.println(diffWaysToCompute("2-1-1"));
        System.out.println(diffWaysToCompute("2*3-4*5"));
    }

    public static List<Integer> diffWaysToCompute(String expression) {
        return dfs(expression.toCharArray(), 0, expression.length() - 1, expression);
    }

    public static List<Integer> dfs(char[] exp, int i, int j, String expression) {
        if (i == j) {
            return Collections.singletonList(Character.digit(exp[i], 10));
        }

        List<Integer> result = new LinkedList<>();

        for (int k = i; k <= j; k++) {
            char c = exp[k];

            // 是运算符
            if (!Character.isDigit(c)) {
                // 左边
                List<Integer> leftList = dfs(exp, i, k - 1, expression);

                // 右边
                List<Integer> rightList = dfs(exp, k + 1, j, expression);

                for (Integer left : leftList) {
                    for (Integer right : rightList) {
                        if (c == '+') {
                            result.add(left + right);
                        } else if (c == '-') {
                            result.add(left - right);
                        } else {
                            result.add(left * right);
                        }
                    }
                }
            }
        }

        if (result.isEmpty()) {
            // 如果集合是空的
            // 那么i~j上一定没有运算符,那么就是一个数字
            result.add(Integer.parseInt(expression.substring(i, j + 1)));
        }

        return result;
    }

}
