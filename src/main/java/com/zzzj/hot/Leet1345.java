package com.zzzj.hot;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-04-04 11:54
 */
public class Leet1345 {

    public static void main(String[] args) {
        System.out.println(minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}));

        System.out.println(minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}));
    }

    public static int minJumps(int[] arr) {

        int N = arr.length;

        Set<Integer> seen = new HashSet<>(N);

        LinkedList<Integer> queue = new LinkedList<>();

        seen.add(0);
        seen.add(-1);

        Map<Integer, List<Integer>> indexes = new HashMap<>();

        for (int i = 0; i < N; i++) {
            indexes.computeIfAbsent(arr[i], integer -> new ArrayList<>())
                    .add(i);
        }

        int ans = 0;

        queue.add(0);

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                Integer rm = queue.removeFirst();

                if (rm == N - 1) {
                    return ans;
                }

                int left = rm - 1;
                int right = rm + 1;

                if (indexes.containsKey(arr[rm])) {
                    for (Integer index : indexes.get(arr[rm])) {
                        if (seen.add(index))
                            queue.addLast(index);
                    }
                    indexes.remove(arr[rm]);
                }

                if (seen.add(left))
                    queue.addLast(left);
                if (seen.add(right))
                    queue.addLast(right);
            }

            ans++;
        }

        return ans;
    }

}
