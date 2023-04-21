package com.zzzj.review;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-03-09 17:46
 */
public class Leet22 {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(1));
    }

    public static List<String> generateParenthesis(int n) {

        List<String> ans = new ArrayList<>();

        backtracking(0, 0, n, ans, new char[(n << 1)], 0);

        return ans;
    }

    public static void backtracking(int leftCount, int rightCount, int n, List<String> ans, char[] path, int pathIndex) {

        if (pathIndex >= path.length) {
            ans.add(String.valueOf(path));
            return;
        }

        // 只能加右括号了
        if (leftCount == n) {
            path[pathIndex] = ')';
            backtracking(leftCount, rightCount + 1, n, ans, path, pathIndex + 1);
            return;
        }

        path[pathIndex] = '(';
        backtracking(leftCount + 1, rightCount, n, ans, path, pathIndex + 1);

        if (rightCount < leftCount) {
            path[pathIndex] = ')';
            backtracking(leftCount, rightCount + 1, n, ans, path, pathIndex + 1);
        }

    }

}
