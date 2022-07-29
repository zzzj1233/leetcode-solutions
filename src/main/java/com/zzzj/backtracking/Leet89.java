package com.zzzj.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-03-26 11:50
 */
public class Leet89 {

    public static void main(String[] args) {
        System.out.println(grayCode(2));
    }

    public static List<Integer> grayCode(int n) {
        int N = (int) StrictMath.pow(2, n);

        List<Integer> ans = new ArrayList<>(N);

        boolean[] visited = new boolean[N];

        LinkedList<Integer> path = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            visited[i] = true;
            path.add(i);

            dfs(ans, n, i, path, visited);

            path.removeLast();
            visited[i] = false;

            if (!ans.isEmpty()) {
                return ans;
            }
        }

        return ans;
    }

    public static void dfs(List<Integer> ans, int n, int cur, LinkedList<Integer> path, boolean[] visited) {
        // 有一位不同
        if (path.size() == visited.length) {
            // 找到最后一个元素
            Integer first = path.peekFirst();

            Integer last = path.peekLast();

            boolean allowDiff = true;

            for (int i = 0; i < 31; i++) {
                if (((first >> i) & 1) != ((last >> i) & 1)) {
                    if (allowDiff) {
                        allowDiff = false;
                    } else {
                        return;
                    }
                }
            }

            ans.addAll(new ArrayList<>(path));

            return;
        }

        // 相同得0,不同得1
        for (int i = 0; i < 31; i++) {
            int next = cur ^ (1 << i);
            if (next < visited.length && !visited[next]) {
                visited[next] = true;
                path.add(next);
                dfs(ans, n, next, path, visited);
                if (!ans.isEmpty()) {
                    return;
                }
                path.removeLast();
                visited[next] = false;
            }
        }

    }

}
