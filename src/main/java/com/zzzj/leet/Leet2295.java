package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

public class Leet2295 {

    public static int[] arrayChange(int[] nums, int[][] operations) {
        int N = nums.length;

        Map<Integer, Integer> map = new HashMap<>(N);

        for (int i = 0; i < N; i++) {
            map.put(nums[i], i);
        }

        for (int[] operation : operations) {
            int origin = operation[0];
            int target = operation[1];

            Integer index = map.get(origin);

            map.remove(origin);
            map.put(target, index);

            nums[index] = target;
        }

        return nums;
    }

}
