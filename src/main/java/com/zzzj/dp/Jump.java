package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;
import com.zzzj.util.Unresolved;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2022-04-03 22:45
 */
public class Jump {

    public static void main(String[] args) {
        int N = 1000;
        for (int i = 0; i < N; i++) {
            int[] arr = ArrayUtil.generateArray(100, 0, 200);

            int start = LeetUtils.random.nextInt(arr.length);
            int end = LeetUtils.random.nextInt(arr.length);

            // int ans1 = minStepsDfs(arr, start, end);
            int ans2 = minStepsBfs(arr, start, end);
            int ans3 = minStepsDp(arr, start, end);

            if (ans2 != ans3) {
                System.out.println("Error");
                return;
            }
        }

        System.out.println("ok~");
    }


    /**
     * 从start的位置到end的位置,最少需要几步
     */
    public static int minStepsDfs(int[] arr, int start, int end) {
        return dfs(arr, end, start, new boolean[arr.length]);
    }

    public static int minStepsBfs(int[] arr, int start, int end) {
        return bfs(arr, start, end);
    }

    public static int minStepsDp(int[] arr, int start, int end) {
        return dp(arr, end, start);
    }

    public static int dfs2(int[] arr, int end, int i, int k) {
        if (k > arr.length - 1) {
            return -1;
        }

        if (i == end) {
            return k;
        }

        int left = i - arr[i];
        int right = i + arr[i];

        int ret = -1;
        int leftRet = -1;
        int rightRet = -1;

        if (left >= 0) {
            leftRet = dfs2(arr, end, left, k + 1);
        }

        if (right < arr.length) {
            rightRet = dfs2(arr, end, right, k + 1);
        }

        if (leftRet != -1 && rightRet != -1) {
            return Math.min(leftRet, rightRet);
        }

        if (leftRet != -1) {
            return leftRet;
        }

        return rightRet;
    }

    // 暴力递归
    public static int dfs(int[] arr, int end, int i, boolean[] visited) {
        if (visited[i]) {
            return -1;
        }

        if (i == end) {
            return 0;
        }

        int left = i - arr[i];
        int right = i + arr[i];

        int ret = -1;
        int leftRet = -1;
        int rightRet = -1;

        visited[i] = true;

        if (left >= 0) {
            leftRet = dfs(arr, end, left, visited);
        }

        if (right < arr.length) {
            rightRet = dfs(arr, end, right, visited);
        }

        visited[i] = false;

        if (leftRet != -1 && rightRet != -1) {
            return Math.min(leftRet, rightRet) + 1;
        }

        if (leftRet != -1) {
            return leftRet + 1;
        }

        return rightRet == -1 ? -1 : rightRet + 1;
    }


    // 广度优先搜索
    public static int bfs(int[] arr, int start, int end) {
        LinkedList<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> level = new HashMap<>();
        queue.add(start);
        level.put(start, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Integer remove = queue.removeFirst();

                Integer curLevel = level.get(remove);

                if (remove == end) {
                    return curLevel;
                }

                int left = remove - arr[remove];
                int right = remove + arr[remove];

                if (left >= 0 && !level.containsKey(left)) {
                    queue.addLast(left);
                    level.put(left, curLevel + 1);
                }

                if (right < arr.length && !level.containsKey(right)) {
                    queue.addLast(right);
                    level.put(right, curLevel + 1);
                }

            }

        }

        return -1;
    }

    // 动态规划
    // k = 已经走过的步数
    @Unresolved
    public static int dp(int[] arr, int end, int start) {
        int[][] dp = new int[arr.length][arr.length + 1];

        for (int i = 0; i < arr.length; i++) {
            dp[i][arr.length] = -1;
        }

        for (int k = 0; k < arr.length; k++) {
            dp[end][k] = k;
        }

        for (int i = 0; i < arr.length; i++) {

            for (int k = arr.length - 1; k >= 0; k--) {

                int left = i - arr[i];
                int right = i + arr[i];

                int leftRet = -1;
                int rightRet = -1;

                if (left >= 0) {
                    leftRet = dp[left][k + 1];
                }

                if (right < arr.length) {
                    rightRet = dp[right][k + 1];
                }

                if (leftRet != -1 && rightRet != -1) {
                    dp[i][k] = Math.min(left, right);
                } else if (leftRet != -1) {
                    dp[i][k] = leftRet;
                } else {
                    dp[i][k] = rightRet;
                }
            }

        }

        return dp[start][0];
    }

}
