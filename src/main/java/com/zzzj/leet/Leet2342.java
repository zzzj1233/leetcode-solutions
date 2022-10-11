package com.zzzj.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-10-11 19:39
 */
public class Leet2342 {

    public static void main(String[] args) {
        System.out.println(maximumSum(new int[]{18, 43, 36, 13, 7}));
    }

    public static int maximumSum(int[] nums) {

        int N = nums.length;

        Map<Integer, Integer> map = new HashMap<>(N);

        int ans = -1;

        for (int num : nums) {
            int sum = sum(num);

            if (map.containsKey(sum)) {
                Integer old = map.get(sum);
                if (num > old) {
                    map.put(sum, num);
                }
                ans = Math.max(ans, old + num);
            } else {
                map.put(sum, num);
            }
        }

        return ans;
    }

    public static int sum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }


}
