package com.zzzj.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-05-05 12:24
 */
public class Leet2453 {

    public static void main(String[] args) {

        System.out.println(destroyTargets(new int[]{3, 7, 8, 1, 1, 5}, 2));

        System.out.println(destroyTargets(new int[]{1, 3, 5, 2, 4, 6}, 2));

        System.out.println(destroyTargets(new int[]{6, 2, 5}, 100));

    }

    public static int destroyTargets(int[] nums, int space) {

        int N = nums.length;

        Map<Integer, int[]> map = new HashMap<>(N);

        int maxCnt = 0;

        for (int i = 0; i < N; i++) {
            int mod = nums[i] % space;

            if (!map.containsKey(mod)) {
                map.put(mod, new int[]{
                        0, Integer.MAX_VALUE
                });
            }

            int[] arr = map.get(mod);

            arr[0]++;
            arr[1] = Math.min(nums[i], arr[1]);

            maxCnt = Math.max(maxCnt, arr[0]);
        }

        int ans = Integer.MAX_VALUE;

        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            if (entry.getValue()[0] == maxCnt) {
                ans = Math.min(ans, entry.getValue()[1]);
            }
        }

        return ans;
    }

}
