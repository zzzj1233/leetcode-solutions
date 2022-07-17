package com.zzzj.leet;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-07-10 16:39
 */
public class Leet1229 {


    public static void main(String[] args) {
        // [[10,12],[15, 25]]
        // [[0,100]]
        // 8
        System.out.println(minAvailableDuration(LeetUtils.convertInts("[[10,12],[15, 25]]"), LeetUtils.convertInts("[[0,100]]"), 8));
    }


    public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {

        Arrays.sort(slots1, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(slots2, Comparator.comparingInt(o -> o[0]));

        int i = 0;
        int j = 0;

        int N = slots1.length;
        int M = slots2.length;

        while (i < N && j < M) {

            int[] slot1 = slots1[i];

            int[] slot2 = slots2[j];

            int start = Math.max(slot1[0], slot2[0]);
            int end = Math.min(slot1[1], slot2[1]);

            if (end - start >= duration) {
                return Arrays.asList(start, start + duration);
            }

            if (slot1[1] < slot2[1]) {
                i++;
                if (i < N && slots1[i][0] > slot2[1]) {
                    j++;
                }
            } else {
                j++;
                if (j < N && slots2[j][0] > slot1[1]) {
                    i++;
                }
            }
        }

        return Collections.emptyList();
    }

}
