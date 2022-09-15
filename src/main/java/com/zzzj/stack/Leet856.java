package com.zzzj.stack;

/**
 * @author zzzj
 * @create 2022-09-13 15:44
 */
public class Leet856 {


    public static void main(String[] args) {
        System.out.println(scoreOfParentheses("(()(()))"));
        System.out.println(scoreOfParentheses("(()(())())"));
        System.out.println(scoreOfParentheses("(((())))"));
        System.out.println(scoreOfParentheses("()"));
        System.out.println(scoreOfParentheses("()()"));
    }

    // (()(()))
    // 2 * ( 1 + 2 * (1))

    // "(((())))"
    public static int scoreOfParentheses(String s) {
        int leftCount = 0;

        int N = s.length();

        int ans = 0;

        for (int i = 0; i < N; ) {
            char c = s.charAt(i);
            if (c == '(') {
                leftCount++;
                i++;
            } else {
                ans += Math.pow(2, leftCount - 1);
                while (i < N && s.charAt(i) == ')') {
                    i++;
                    leftCount--;
                }
            }
        }

        return ans;
    }


}
