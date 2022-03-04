package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-03-04 12:11
 */
public class Leet1215 {

    public static void main(String[] args) {
        // 0
        // 1000000000
        System.out.println(countSteppingNumbers(0, 1000000000).size());

        System.out.println(right(0, 1000000000).size());
    }

    public static List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> ans = new ArrayList<>();

        LinkedList<Integer> path = new LinkedList<>();

        if (low == 0) {
            ans.add(0);
        }

        for (int i = 1; i <= Math.min(9, high); i++) {
            path.add(i);
        }

        bfs(high, low, ans, path);

        return ans;
    }

    public static void bfs(int max, int low, List<Integer> ans, LinkedList<Integer> path) {

        while (!path.isEmpty()) {
            int first = path.removeFirst();

            if (first <= max) {
                if (first >= low) {
                    ans.add(first);
                }

                if (first > max / 10) {
                    continue;
                }

                int add = first * 10 + first % 10 + 1;
                int sub = first * 10 + first % 10 - 1;

                if (first % 10 > 0 && sub <= max) {
                    path.add(sub);
                }

                if (first % 10 < 9 && add <= max) {
                    path.add(add);
                }

            }
        }

    }

    public static List<Integer> right(int low, int high) {
        List<Integer> result = new ArrayList<>();
        if (low == 0) {
            result.add(0);
        }
        for (int i = 1; i <= 9; i++) {
            dfs(result, i, low, high);
        }
        Collections.sort(result);
        return result;
    }

    public static void dfs(List<Integer> result, int cur, int low, int high) {
        if (cur >= low && cur <= high) {
            result.add(cur);
        }
        if (cur > high / 10) {
            return;
        }
        int r = cur % 10;
        if (r != 9 && cur * 10 + r + 1 <= high) {
            dfs(result, cur * 10 + r + 1, low, high);
        }
        if (r != 0 && cur * 10 + r - 1 <= high) {
            dfs(result, cur * 10 + r - 1, low, high);
        }
    }


}
