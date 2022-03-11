package com.zzzj.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zzzj
 * @create 2022-03-11 17:45
 */
public class Leet506 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRelativeRanks(new int[]{5, 4, 3, 2, 1})));
    }

    public static final String[] AWARD = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};

    public static String[] findRelativeRanks(int[] score) {
        String[] ans = new String[score.length];

        PriorityQueue<int[]> queue = new PriorityQueue<>(score.length, Comparator.comparingInt(o -> ((int[]) o)[1]).reversed());

        for (int i = 0; i < score.length; i++) {
            queue.add(new int[]{i, score[i]});
        }

        int size = queue.size();

        for (int i = 0; i < size; i++) {
            int[] item = queue.remove();
            ans[item[0]] = i > 2 ? String.valueOf(i + 1) : AWARD[i];
        }

        return ans;
    }

}
