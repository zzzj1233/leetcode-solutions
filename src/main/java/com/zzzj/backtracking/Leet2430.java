package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-03-29 11:13
 */
public class Leet2430 {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();

        dfs(ans, n , 0, new char[n << 1], 0);

        return ans;
    }

    public static final char LEFT = '(';

    public static final char RIGHT = ')';

    public static void dfs(List<String> list, int leftCount, int rightCount, char[] path, int index) {
        if (leftCount == 0 && rightCount == 0) {
            list.add(String.valueOf(path));
            return;
        }
        if (leftCount > 0) {
            path[index] = LEFT;
            dfs(list, leftCount - 1, rightCount + 1, path, index + 1);
        }
        if (rightCount > 0) {
            path[index] = RIGHT;
            dfs(list, leftCount, rightCount - 1, path, index + 1);
        }
    }

}
