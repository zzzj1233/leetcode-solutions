package com.zzzj.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-08-24 18:08
 */
public class Leet1570 {


    private static class SparseVector {

        private final Map<Integer, Integer> map;

        SparseVector(int[] nums) {
            map = new HashMap<>(nums.length);

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    map.put(i, nums[i]);
                }
            }
        }

        public int dotProduct(SparseVector vec) {
            if (this.map.isEmpty() || vec.map.isEmpty()) {
                return 0;
            }

            int ans = 0;

            for (Map.Entry<Integer, Integer> entry : this.map.entrySet()) {
                Integer index = entry.getKey();
                if (!vec.map.containsKey(index)) {
                    continue;
                }
                ans += vec.map.get(index) * entry.getValue();
            }

            return ans;
        }
    }
}
