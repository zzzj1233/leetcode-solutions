package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-03-29 16:25
 */
public class Leet2425 {

    public static void main(String[] args) {
        System.out.println(combine(4, 2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();

        dfs(ans, new LinkedList<>(), 0, n, k);

        return ans;
    }

    public static void dfs(List<List<Integer>> ans, LinkedList<Integer> path, int cur, int n, int k) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = cur + 1; i <= n; i++) {
            path.add(i);
            dfs(ans, path, i, n, k);
            path.removeLast();
        }
    }

}
