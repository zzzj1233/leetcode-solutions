package com.zzzj.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2024-01-17 15:14
 */
public class Leet2025 {

    public static void main(String[] args) {

        System.out.println(waysToPartition(new int[]{27335, -27335, 0, 0, 0, 0, 97328, -97328, 0, 79944, -79944, 0, 0, 0, 0, -73546, 73546, 0, 0, 0, 0, -62146, 62146, 55310, -55310, 0, 0, 0, 0, -17202, 17202, 0, 0, 0, 18526, -18526, 41634, -41634, 0, 46865, -46865, 0, 40195, -40195, 0, 79602, -79602, 0, 0, 0, 0, 73130, -73130, 0, 82429, -82429, 0, 92028, -92028, 0, 0, 0, 0, 27539, -27539, 0, 136, -136, 0, 0, 54670, -54670, 0, 0, -95468, 95468, 0, 0, 0, 0, 48155, -48155, 0}, 73874));

        System.out.println(waysToPartition(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30827, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0));

        System.out.println(waysToPartition(new int[]{0, 0, 0}, 1));

        System.out.println(waysToPartition(new int[]{2, -1, 2}, 3));

        System.out.println(waysToPartition(new int[]{22, 4, -25, -20, -15, 15, -16, 7, 19, -10, 0, -13, -14}, -33));

    }

    public static int waysToPartition(int[] nums, int k) {


        // 某个前缀和 == sum / 2 , 那么就找到了分割点

        int N = nums.length;

        int[] preSum = new int[N];

        preSum[0] = nums[0];

        int ans = 0;

        // 不改变的情况
        for (int i = 1; i < N; i++)
            preSum[i] = preSum[i - 1] + nums[i];

        Map<Integer, Integer> left = new HashMap<>(N);

        Map<Integer, Integer> right = new HashMap<>(N);

        for (int i = 1; i < N; i++)
            right.put(preSum[i - 1], right.getOrDefault(preSum[i - 1], 0) + 1);

        int sum = preSum[N - 1];

        if ((sum & 1) == 0)
            ans = right.getOrDefault(sum / 2, 0);

        for (int i = 0; i < N; i++) {

            int diff = k - nums[i];

            // x -> k
            // diff = k - x
            // 左边的前缀和不变 [ 0 ~ x )
            // 右边的前缀和+diff [ x - N )
            // sum + diff

            int s = sum + diff;

            if (s % 2 == 0)
                ans = Math.max(
                        ans,
                        left.getOrDefault(s / 2, 0) +
                                right.getOrDefault(s / 2 - diff, 0)
                );

            Integer old = right.get(preSum[i]);

            if (old != null) {
                left.put(preSum[i], left.getOrDefault(preSum[i], 0) + 1);

                if (old == 1)
                    right.remove(preSum[i]);
                else
                    right.put(preSum[i], old - 1);
            }

        }

        return ans;
    }

}
