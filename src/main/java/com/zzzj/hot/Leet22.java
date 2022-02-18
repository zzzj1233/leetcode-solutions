package com.zzzj.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-01-25 16:20
 */
public class Leet22 {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        char[] path = new char[n << 1];

        List<String> ans = new ArrayList<>();

        process(ans, n, 0, path, 0);

        return ans;
    }

    public static void process(List<String> ans, int remainLeftCount, int remainRightCount, char[] path, int index) {
        if (index == path.length) {
            ans.add(String.valueOf(path));
            return;
        }

        if (remainLeftCount > 0) {
            path[index] = '(';
            process(ans, remainLeftCount - 1, remainRightCount + 1, path, index + 1);
        }

        if (remainRightCount > 0) {
            path[index] = ')';
            process(ans, remainLeftCount, remainRightCount - 1, path, index + 1);
        }

    }

}
