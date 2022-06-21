package com.zzzj.dp;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-06-21 14:21
 */
public class Leet678 {

    public static void main(String[] args) {
        System.out.println(checkValidString("("));
    }

    public static boolean checkValidString(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        LinkedList<Integer> leftStack = new LinkedList<>();
        LinkedList<Integer> starStack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.addLast(i);
            } else if (c == ')') {
                if (leftStack.isEmpty() && starStack.isEmpty()) {
                    return false;
                }
                if (!leftStack.isEmpty()) {
                    leftStack.removeLast();
                } else {
                    starStack.removeLast();
                }
            } else if (c == '*') {
                starStack.addLast(i);
            } else {
                // invalid char
                return false;
            }
        }


        while (!leftStack.isEmpty()) {
            Integer last = leftStack.removeLast();
            // start必须在left的右边
            if (starStack.isEmpty() || starStack.removeLast() < last) {
                return false;
            }
        }

        return true;
    }


}
