package com.zzzj.hot;

import java.util.LinkedList;

/**
 * @author Zzzj
 * @create 2022-04-04 11:50
 */
public class Leet1036 {

    public static boolean canReach(int[] arr, int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer remove = queue.removeFirst();
                if (arr[remove] == 0) {
                    return true;
                }

                int left = remove - arr[remove];
                int right = remove + arr[remove];

                if (left >= 0 && !visited[left]) {
                    queue.addLast(left);
                    visited[left] = true;
                }

                if (right < arr.length && !visited[right]) {
                    queue.addLast(right);
                    visited[right] = true;
                }
            }
        }

        return false;
    }

}
