package com.zzzj.arr;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author zzzj
 * @create 2023-08-25 12:34
 */
public class Leet2382 {

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(maximumSegmentSum(new int[]{1, 2, 5, 6, 1}, new int[]{0, 3, 2, 4, 1})));
//
//        System.out.println(Arrays.toString(maximumSegmentSum(new int[]{3, 2, 11, 1}, new int[]{3, 2, 1, 0})));


        // [6,5,0,2,0]
        // [6,5,5,2,0]

        // 123[3]5
        // [1]23/5
        //
        System.out.println(Arrays.toString(maximumSegmentSum(new int[]{1, 2, 3, 4, 5}, new int[]{3, 0, 4, 2, 1})));

    }

    public static long[] maximumSegmentSum(int[] nums, int[] removeQueries) {

        int N = nums.length;

        long[] preSum = new long[N + 1];

        for (int i = 1; i <= N; i++) preSum[i] = preSum[i - 1] + nums[i - 1];

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(N - 1, 0);

        int M = removeQueries.length;

        long[] ans = new long[M];

        TreeMap<Long, Integer> maxMap = new TreeMap<>();

        maxMap.put(preSum[N], 1);

        for (int i = 0; i < M; i++) {

            int index = removeQueries[i];

            Map.Entry<Integer, Integer> ceiling = map.ceilingEntry(index);

            Integer right = ceiling.getKey();

            Integer left = ceiling.getValue();

            // prev
            long prev = preSum[right + 1] - preSum[left];

            sub(maxMap, prev);

            // left
            long leftSum = preSum[index] - preSum[left];
            long rightSum = preSum[right + 1] - preSum[index + 1];

            maxMap.put(leftSum, maxMap.getOrDefault(leftSum, 0) + 1);
            maxMap.put(rightSum, maxMap.getOrDefault(rightSum, 0) + 1);

            map.remove(ceiling.getKey());

            map.put(index - 1, left);
            map.put(right, index + 1);

            ans[i] = maxMap.lastEntry().getKey();
        }

        return ans;
    }

    public static void sub(TreeMap<Long, Integer> map, Long key) {
        Integer old = map.get(key);
        if (old == 1)
            map.remove(key);
        else
            map.put(key, old - 1);
    }
}
