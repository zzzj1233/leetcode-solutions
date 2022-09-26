package com.zzzj.greedy;

import com.zzzj.leet.LeetUtils;

/**
 * @author zzzj
 * @create 2022-09-16 11:22
 */
public class Leet1541 {

    public static void main(String[] args) {

//        System.out.println(minInsertions("()()))"));
//
//        System.exit(0);

        for (int i = 0; i < 1000; i++) {
            final String str = LeetUtils.randomString0("()", 6);
            if (minInsertions(str) != right(str)) {
                System.out.println("Error");
                System.out.println(str);
                System.out.println(minInsertions(str));
                System.out.println(right(str));
                return;
            }
        }
        System.out.println("Ok");
    }


    public static int minInsertions(String s) {
        int N = s.length();

        int leftCount = 0;

        int rightCount = 0;

        int ans = 0;

        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftCount++;
            } else {
                boolean nextRight = i + 1 < N && s.charAt(i + 1) == ')';
                if (leftCount == 0) {
                    ans += nextRight ? 1 : 2;
                } else {
                    leftCount--;
                    ans += nextRight ? 0 : 1;
                }
                if (nextRight) {
                    i++;
                }
            }
        }
        return ans + (leftCount << 1);
    }

    public static int right(String s) {
        char[] chars = s.toCharArray();
        int res = 0, N = chars.length, left = 0;
        for (int i = 0; i < N; i++) {
            if (chars[i] == '(') {
                left++;
            } else {
                // 没有左括号了，需要加一个左括号，res++
                if (left == 0) res++;
                else left--;

                // 以下两种情况只有一个右括号，需要再加一个右括号，res++
                if (i == N - 1 || chars[i + 1] != ')') res++;
                else i++;
            }
        }
        return res + left * 2; // 剩余的左括号需要2倍的右括号匹配
    }

}
