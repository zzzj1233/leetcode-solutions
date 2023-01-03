package com.zzzj.stack;

/**
 * @author zzzj
 * @create 2022-12-30 17:07
 */
public class Leet1249 {

    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(minRemoveToMakeValid("a(b(c)d"));
        System.out.println(minRemoveToMakeValid("))(("));
    }

    public static String minRemoveToMakeValid(String s) {
        // 1. 左边比右边多 -> 有补救机会

        // 2. 右边比左边多 -> 无补救机会

        int left = 0;
        int right = 0;

        int N = s.length();

        StringBuilder builder = new StringBuilder(s.length());

        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                left++;
            } else if (c == ')') {
                right++;
            }

            if (right > left) {
                right = left;
            } else {
                builder.append(c);
            }
        }

        // 移除多余的左括号

        if (left != right) {
            StringBuilder ans = new StringBuilder(builder.length());

            for (int i = builder.length() - 1; i >= 0; i--) {
                char c = builder.charAt(i);
                if (c == '(' && left != right) {
                    left--;
                } else {
                    ans.append(c);
                }
            }
            return ans.reverse().toString();
        } else {
            return builder.toString();
        }
    }

}
