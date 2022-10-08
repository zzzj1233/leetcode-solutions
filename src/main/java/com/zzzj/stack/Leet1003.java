package com.zzzj.stack;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2022-10-08 13:07
 */
public class Leet1003 {

    public static final int A = 1;
    public static final int B = 2;
    public static final int C = 3;
    public static final int AB = 4;
    public static final int BC = 5;
    public static final int ABC = -1;

    // "aaabc"
    public static void main(String[] args) {
        System.out.println(isValid("aabcbc"));
        System.out.println(isValid("ababcc"));
        System.out.println(isValid("abccba"));
    }

    public static boolean isValid(String s) {

        LinkedList<Integer> stack = new LinkedList<>();

        int index = 0; // ababcc

        int N = s.length();

        while (index < N) {
            int type = check(s, index);
            switch (type) {
                case A:
                    stack.addLast(A);
                    index += 1;
                    break;
                case B:
                    if (stack.isEmpty() || stack.peekLast() != A) {
                        return false;
                    }
                    stack.addLast(B);
                    index += 1;
                    break;
                case C:
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.peekLast() == B) {
                        stack.removeLast();
                        stack.removeLast();
                        index += 1;
                    } else if (stack.peekLast() == AB) {
                        stack.removeLast();
                        index += 1;
                    } else {
                        return false;
                    }
                    break;
                case AB:
                    stack.addLast(AB);
                    index += 2;
                    break;
                case BC:
                    if (stack.isEmpty() || stack.peekLast() != A) {
                        return false;
                    }
                    stack.removeLast();
                    index += 2;
                    break;
                default: // ABC
                    index += 3;
            }
        }

        return stack.isEmpty();
    }

    public static int check(String str, int index) {
        if (str.charAt(index) == 'c') {
            return C;
        } else if (str.charAt(index) == 'b') {
            if (index + 1 < str.length() && str.charAt(index + 1) == 'c') {
                return BC;
            }
            return B;
        } else {
            if (index + 2 < str.length() && str.charAt(index + 1) == 'b' && str.charAt(index + 2) == 'c') {
                return ABC;
            } else if (index + 1 < str.length() && str.charAt(index + 1) == 'b') {
                return AB;
            }
            return A;
        }
    }

}
