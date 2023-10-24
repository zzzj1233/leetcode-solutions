package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-10-24 11:21
 */
public class Leet1298 {

    public static void main(String[] args) {

        System.out.println(maxCandies(
                new int[]{1, 0, 1, 0},
                new int[]{7, 5, 4, 100},
                LeetUtils.convertInts("[[],[],[],[]]"),
                LeetUtils.convertInts("[[1,2],[3],[],[]]"),
                new int[]{1, 2}
        ));

    }

    public static int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {

        LinkedList<Integer> queue = new LinkedList<>();

        Set<Integer> locked = new HashSet<>();

        Set<Integer> obtainKeys = new HashSet<>();

        for (int initialBox : initialBoxes) {
            if (status[initialBox] == 1)
                queue.add(initialBox);
            else
                locked.add(initialBox);
        }

        int ans = 0;

        while (!queue.isEmpty()) {

            Integer box = queue.removeFirst();

            ans += candies[box];

            int[] innerBox = containedBoxes[box];

            for (int key : keys[box]) {
                if (locked.remove(key))
                    queue.add(key);
                else
                    obtainKeys.add(key);
            }

            for (int inner : innerBox) {
                if (status[inner] == 0) {
                    if (obtainKeys.remove(inner))
                        queue.add(inner);
                    else
                        locked.add(inner);
                } else
                    queue.add(inner);
            }

        }

        return ans;
    }

}
