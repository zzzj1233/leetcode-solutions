package com.zzzj.window;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2021-12-21 23:51
 */
public class Leet1695 {

    public static void main(String[] args) {
//        int[] arr = ArrayUtil.generateArray(5, 1, 5);
//
//        System.out.println(Arrays.toString(arr));

        System.out.println(maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5}));
    }

    public static int maximumUniqueSubarray(int[] nums) {
        Map<Integer, Integer> record = new HashMap<>(nums.length);

        int i = 0;

        int j = 0;

        int[] sum = new int[nums.length + 1];

        for (int k = 1; k < nums.length; k++) {
            sum[k] = sum[k - 1] + nums[k - 1];
        }

        int ans = 0;

        int max = 0;
        while (j < nums.length) {
            int value = nums[j];
            Integer index = record.get(value);
            if (index != null && index >= i) {
                ans -= sum[index + 1] - sum[i];
                i = index + 1;
            }
            record.put(value, j);
            ans += value;
            max = Math.max(ans, max);
            j++;
        }

        return max;
    }

}
