package com.zzzj.leet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-10-27 09:50
 */
public class Leet301 {

    /**
     * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "()())()"
     * 输出：["(())()","()()()"]
     * 示例 2：
     * <p>
     * 输入：s = "(a)())()"
     * 输出：["(a())()","(a)()()"]
     * 示例 3：
     * <p>
     * 输入：s = ")("
     * 输出：[""]
     * <p>
     * 我们可以使用递归来尝试给定表达式的所有可能性。对于每个括号，我们有两个选项：
     * 我们保留括号，并将其添加到递归过程中动态构建的表达式中。
     * 或者，我们可以放弃括号，继续前进。
     */

    public static List<String> removeInvalidParentheses(String s) {
        // 回溯
        // bfs
        final char left = '(';
        final char right = ')';
        return null;
    }

    private static void bfs(String s) {
        LinkedList<String> queue = new LinkedList<>();
        queue.add(s);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String first = queue.removeFirst();

            }
        }
    }

}
