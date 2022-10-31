package com.zzzj.tree;

import java.util.TreeSet;

public class Leet683 {

    public static void main(String[] args) {
        System.out.println(kEmptySlots(new int[]{1, 3, 2}, 1));
    }


    public static int kEmptySlots(int[] bulbs, int k) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < bulbs.length; i++) {
            int bulb = bulbs[i];
            Integer floor = set.floor(bulb);
            Integer ceiling = set.ceiling(bulb);
            if (floor != null && floor == bulb - k - 1) {
                return i + 1;
            }
            if (ceiling != null && ceiling == bulb + k + 1) {
                return i + 1;
            }
            set.add(bulbs[i]);
        }

        return -1;
    }

}
