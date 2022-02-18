package com.zzzj.hot;

import cn.hutool.core.lang.Assert;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-01-25 14:39
 */
public class Leet20 {

    public static void main(String[] args) {
        Assert.isTrue(isValid("()"));
        Assert.isTrue(isValid("()[]{}"));
        Assert.isFalse(isValid("(]"));
        Assert.isFalse(isValid("([)]"));
        Assert.isTrue(isValid("{[]}"));
    }

    public static boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.addLast(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (c == ')') {
                    if (stack.peekLast() != '(') {
                        return false;
                    }

                } else if (c == ']') {
                    if (stack.peekLast() != '[') {
                        return false;
                    }
                } else {
                    if (stack.peekLast() != '{') {
                        return false;
                    }
                }
                stack.removeLast();
            }
        }

        return stack.isEmpty();
    }

}
