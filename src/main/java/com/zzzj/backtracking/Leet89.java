package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-03-26 11:50
 */
public class Leet89 {

    public static List<Integer> grayCode(int n) {
        int N = (int) StrictMath.pow(2, n);

        List<Integer> ans = new ArrayList<>(N);

        ans.add(0);

        dfs(ans, N);

        return ans;
    }

    public static void dfs(List<Integer> ans, int n) {
        if (ans.size() == n) {
            return;
        }
        // 有一位不同

    }

}
