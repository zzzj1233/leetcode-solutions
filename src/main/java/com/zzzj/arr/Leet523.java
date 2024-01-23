package com.zzzj.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-12-26 16:56
 */
public class Leet523 {

    public static void main(String[] args) {

        System.out.println(checkSubarraySum(new int[]{23, 2, 4, 6, 6}, 7));

    }

    public static boolean checkSubarraySum(int[] nums, int k) {

        int N = nums.length;

        int mod = 0;

        Map<Integer, Integer> rec = new HashMap<>();

        rec.put(0, -1);

        for (int i = 0; i < N; i++) {

            mod = (mod + nums[i]) % k;

            Integer old = rec.get(mod);

            if (old != null) {
                if (i - old > 1)
                    return true;
            } else
                rec.put(mod, i);
        }

        return false;
    }

}
