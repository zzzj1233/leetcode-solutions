package com.zzzj.arr;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leet1899 {

    public static void main(String[] args) {
        System.out.println(mergeTriplets(LeetUtils.convertInts("[[2,5,3],[1,8,4],[1,7,5]]"), new int[]{2, 7, 5}));
    }

    public static boolean mergeTriplets(int[][] triplets, int[] target) {

        int N = triplets.length;

        HashMap<Integer, List<Integer>> one = new HashMap<>(N);
        HashMap<Integer, List<Integer>> two = new HashMap<>(N);
        HashMap<Integer, List<Integer>> three = new HashMap<>(N);


        for (int i = 0; i < N; i++) {
            int[] triplet = triplets[i];
            one.computeIfAbsent(triplet[0], integer -> new ArrayList<>())
                    .add(i);
            two.computeIfAbsent(triplet[1], integer -> new ArrayList<>())
                    .add(i);
            three.computeIfAbsent(triplet[2], integer -> new ArrayList<>())
                    .add(i);
        }

        return check(triplets, one, target, 0) && check(triplets, two, target, 1) && check(triplets, three, target, 2);
    }

    public static boolean check(int[][] triplets, HashMap<Integer, List<Integer>> map, int[] target, int index) {

        List<Integer> list = map.get(target[index]);

        if (list == null) {
            return false;
        }

        OUTER:
        for (Integer it : list) {
            int[] triplet = triplets[it];

            for (int i = 0; i < triplet.length; i++) {
                if (i == index) {
                    continue;
                }
                if (triplet[i] > target[i]) {
                    continue OUTER;
                }
            }

            return true;
        }

        return false;
    }

}
