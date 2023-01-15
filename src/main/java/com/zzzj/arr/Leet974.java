package com.zzzj.arr;

import java.util.HashMap;

public class Leet974 {

    public static int subarraysDivByK(int[] nums, int k) {

        HashMap<Integer, Integer> count = new HashMap<>();

        count.put(0, 1);

        int sum = 0;

        int N = nums.length;

        for (int i = 0; i < N; i++) {
            int num = nums[i];
            sum += num;

        }

        return -1;
    }

}
