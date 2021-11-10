package com.zzzj.leet;

import java.util.LinkedList;

/**
 * @author zzzj
 * @create 2021-09-26 15:20
 */
public class Leet20 {

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "()"
     * 输出：true
     * 示例2：
     * <p>
     * 输入：s = "()[]{}"
     * 输出：true
     * 示例3：
     * <p>
     * 输入：s = "(]"
     * 输出：false
     * 示例4：
     * <p>
     * 输入：s = "([)]"
     * 输出：false
     * 示例5：
     * <p>
     * 输入：s = "{[]}"
     * 输出：true
     */

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
    }

    public static boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.add(c);
                    break;
                case ')':
                    if (stack.isEmpty()){
                        return false;
                    }
                    Character last = stack.removeLast();
                    if (last != '(') {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty()){
                        return false;
                    }
                    Character last2 = stack.removeLast();
                    if (last2 != '{') {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty()){
                        return false;
                    }
                    Character last3 = stack.removeLast();
                    if (last3 != '[') {
                        return false;
                    }
                    break;
            }
        }

        return stack.isEmpty();
    }

}
