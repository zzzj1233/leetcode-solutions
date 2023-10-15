package com.zzzj.contest.week367;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q3 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(findIndices(new int[]{5, 1, 4, 1}, 2, 4)));

        System.out.println(Arrays.toString(findIndices(new int[]{2, 1}, 0, 0)));

        System.out.println(Arrays.toString(findIndices(new int[]{1, 2, 3}, 2, 4)));

        // [7,10,4,5,1]
        // 2
        // 6
        System.out.println(Arrays.toString(findIndices(new int[]{7, 10, 4, 5, 1}, 2, 6)));
    }

    public static int[] findIndices(int[] nums, int indexDifference, int valueDifference) {

        int N = nums.length;

        if (N == 1)
            return indexDifference == 0 && valueDifference == 0 ? new int[2] : new int[]{-1, -1};

        BITree tree = new BITree(N);

        Map<Integer, Integer> pos = new HashMap<>(N);

        for (int i = 0; i < N; i++) {
            if (i - indexDifference >= 0) {
                int[] query = tree.query(i - indexDifference);
                int maxValue = query[0];
                if (Math.abs(maxValue - nums[i]) >= valueDifference && pos.containsKey(maxValue))
                    return new int[]{pos.get(maxValue), i};
                int minValue = query[1];
                if (Math.abs(minValue - nums[i]) >= valueDifference && pos.containsKey(minValue))
                    return new int[]{pos.get(minValue), i};
            }
            tree.update(i, nums[i]);
            pos.putIfAbsent(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    private static class BITree {

        public final int[] max;

        public final int[] min;

        private BITree(int N) {
            this.max = new int[N + 1];
            this.min = new int[N + 1];
            Arrays.fill(max, Integer.MIN_VALUE);
            Arrays.fill(min, Integer.MAX_VALUE);
        }

        public void update(int i, int value) {
            int index = i + 1;

            while (index < max.length) {
                max[index] = Math.max(max[index], value);
                min[index] = Math.min(min[index], value);
                index += lowbit(index);
            }
        }

        public int[] query(int i) {

            int[] result = {Integer.MIN_VALUE, Integer.MAX_VALUE};

            int index = i + 1;

            while (index > 0) {
                result[0] = Math.max(result[0], max[index]);
                result[1] = Math.min(result[1], min[index]);
                index -= lowbit(index);
            }

            return result;
        }

        private int lowbit(int x) {
            return x & (-x);
        }

    }

}
