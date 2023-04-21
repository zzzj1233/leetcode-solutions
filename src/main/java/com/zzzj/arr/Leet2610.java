package com.zzzj.arr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-04-12 17:31
 */
public class Leet2610 {

    public static void main(String[] args) {
        System.out.println(findMatrix(new int[]{1, 3, 4, 1, 2, 3, 1}));
    }

    public static List<List<Integer>> findMatrix(int[] nums) {

        int N = nums.length;

        int count = nums.length;

        int[] table = new int[201];

        for (int i = 0; i < N; i++) {
            table[nums[i]]++;
        }

        List<List<Integer>> ans = new ArrayList<>();


        while (count > 0) {

            List<Integer> line = new ArrayList<>();

            for (int i = 0; i <= 200; i++) {

                if (table[i] > 0) {
                    table[i]--;
                    count--;
                    line.add(i);
                }
            }

            ans.add(line);

        }


        return ans;
    }

}
