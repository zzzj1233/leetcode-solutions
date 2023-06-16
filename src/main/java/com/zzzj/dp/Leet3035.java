package com.zzzj.dp;

import com.zzzj.util.Unresolved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-05-26 12:02
 */
@Unresolved
public class Leet3035 {

    public static void main(String[] args) {

        System.out.println(countEval("0&0&0&1^1|0", 1));

    }

    public static int countEval(String s, int result) {

        List<Integer> values = dfs(s.toCharArray(), 0, s.length() - 1);

        int ans = 0;

        for (Integer value : values) {
            if (value == result) {
                ans++;
            }
        }

        return ans;
    }

    final int ZERO = 0;

    final int ONE = 1;

    public static List<Integer> dfs(char[] chars, int left, int right) {

        int currentValue = Character.digit(chars[left], 10);

        List<Integer> results = new ArrayList<>();

        for (int i = left + 1; i < right; i += 2) {

            char op = chars[i];

            int nextValue = Character.digit(chars[i + 1], 10);

            if (left + 2 == right) {
                return Arrays.asList(cal(op, currentValue, nextValue));
            }

            //  case1: 做运算

            switch (op) {
                case '&':
                case '|':
                case '^':
                    // case1: 和dfs后的结果做运算
                    List<Integer> values = dfs(chars, i + 1, right);

                    for (Integer value : values) {
                        results.add(cal(op, currentValue, value));
                    }

                    // case2: 和这个值做运算, 继续循环
                    currentValue = cal(op, currentValue, nextValue);
                    break;
                default:
                    throw new IllegalStateException("Illegal op : " + op);
            }
        }

        results.add(currentValue);

        return results;
    }

    public static int cal(char op, int x, int y) {
        if (op == '&') {
            return x & y;
        } else if (op == '|') {
            return x | y;
        } else {
            return x ^ y;
        }
    }
}
