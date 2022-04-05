package com.zzzj.hot;

import java.util.*;

/**
 * @author Zzzj
 * @create 2022-04-04 11:54
 */
public class Leet1345 {

    public static int minJumps(int[] arr) {

        Map<Integer, Set<Integer>> table = new HashMap<>(arr.length);

        for (int i = 0; i < arr.length; i++) {
            table.computeIfAbsent(arr[i], integer -> new HashSet<>()).add(i);
        }

        LinkedList<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> levelMap = new HashMap<>();
        queue.add(0);
        levelMap.put(0, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Integer remove = queue.removeFirst();

                Integer level = levelMap.get(remove);

                if (remove == arr.length - 1) {
                    return level;
                }

                int left = remove - 1;
                int right = remove + 1;

                if (left >= 0 && !levelMap.containsKey(left)) {
                    queue.add(left);
                    levelMap.put(left, level + 1);
                }

                if (right < arr.length && !levelMap.containsKey(right)) {
                    queue.add(right);
                    levelMap.put(right, level + 1);
                }

                for (Integer range : table.get(arr[remove])) {
                    if (!levelMap.containsKey(range)) {
                        queue.add(range);
                        levelMap.put(range, level + 1);
                    }
                }

            }

        }

        return -1;
    }

}
