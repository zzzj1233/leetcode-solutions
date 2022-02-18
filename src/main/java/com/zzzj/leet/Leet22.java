package com.zzzj.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-10-13 14:16
 */
public class Leet22 {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(4));
    }

    public static List<String> generateParenthesis(int n) {
        char[] init = new char[(n << 1)];
        init[0] = '(';

        List<String> result = new ArrayList<>();

        gen(init, 1, n << 1, result);

        return result;
    }

    public static void gen(char[] charArr, int i, int n, List<String> result) {
        if (i < n) {
            int rightCount = getRightCount(charArr, i);
            int leftCount = i - rightCount;
            if (leftCount < n / 2) {
                char[] copy = new String(charArr).toCharArray();
                copy[i] = '(';
                gen(copy, i + 1, n, result);
            }
            if (rightCount < leftCount) {
                char[] copy = new String(charArr).toCharArray();
                copy[i] = ')';
                gen(copy, i + 1, n, result);
            }
        } else {
            result.add(new String(charArr));
        }
    }

    public static int getRightCount(char[] charArr, int i) {
        int count = 0;
        for (int j = 0; j < i; j++) {
            if (charArr[j] == ')') {
                count++;
            }
        }
        return count;
    }

}
