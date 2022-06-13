package com.zzzj.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-06-07 18:42
 */
public class Leet1213 {

    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int[] table = new int[2000];

        for (int i : arr2) {
            table[i]++;
        }

        for (int i : arr3) {
            table[i]++;
        }

        List<Integer> ans = new ArrayList<>();

        for (int i : arr1) {
            if (table[i] == 2) {
                ans.add(i);
            }
        }

        return ans;
    }


}
