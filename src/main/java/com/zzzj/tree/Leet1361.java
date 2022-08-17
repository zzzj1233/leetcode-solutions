package com.zzzj.tree;

/**
 * @author zzzj
 * @create 2022-08-16 17:16
 */
public class Leet1361 {

    public static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int root = findRoot(n, leftChild, rightChild);

        if (root == -1) {
            return false;
        }

        boolean[] visited = new boolean[n];

        return dfs(root, n, leftChild, rightChild, visited) && allMatch(visited);
    }

    public static boolean allMatch(boolean[] visited) {
        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public static boolean dfs(int root, int n, int[] leftChild, int[] rightChild, boolean[] visited) {
        if (visited[root]) {
            return false;
        }

        visited[root] = true;

        int left = leftChild[root];

        int right = rightChild[root];

        boolean ret = true;

        if (left >= 0) {
            ret &= dfs(left, n, leftChild, rightChild, visited);
        }

        if (right >= 0) {
            ret &= dfs(right, n, leftChild, rightChild, visited);
        }

        return ret;
    }

    public static int findRoot(int n, int[] leftChild, int[] rightChild) {
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (leftChild[i] < 0) {
                continue;
            }
            if (visited[leftChild[i]]) {
                return -1;
            }
            visited[leftChild[i]] = true;
        }

        for (int i = 0; i < n; i++) {
            if (rightChild[i] < 0) {
                continue;
            }
            if (visited[rightChild[i]]) {
                return -1;
            }
            visited[rightChild[i]] = true;
        }

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (root > 0) {
                    return -1;
                }
                root = i;
            }
        }
        return root;
    }


}
