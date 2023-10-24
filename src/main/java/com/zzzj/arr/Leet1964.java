package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-20 16:03
 */
public class Leet1964 {

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(longestObstacleCourseAtEachPosition(new int[]{1, 2, 3, 2})));

        System.out.println(Arrays.toString(longestObstacleCourseAtEachPosition(new int[]{3, 1, 5, 6, 4, 2})));

    }

    public static int[] longestObstacleCourseAtEachPosition(int[] obstacles) {

        int N = obstacles.length;

        int[] ans = new int[N];

        int[] sorted = Arrays.stream(obstacles)
                .sorted()
                .toArray();

        BIT tree = new BIT(N);

        for (int i = 0; i < N; i++) {

            int left = 0;

            int right = N - 1;

            int index = -1;

            int value = obstacles[i];

            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (sorted[mid] <= value) {
                    left = mid + 1;
                    index = mid;
                } else {
                    right = mid - 1;
                }
            }

            if (index == -1)
                ans[i] = 1;
            else
                ans[i] = tree.query(index) + 1;

            tree.update(index, ans[i]);
        }

        return ans;
    }

    private static class BIT {

        private final int[] data;

        private BIT(int N) {
            this.data = new int[N + 1];
        }

        public void update(int i, int value) {
            int index = i + 1;
            while (index < data.length) {
                data[index] = Math.max(data[index], value);
                index += lowbit(index);
            }
        }

        public int query(int i) {

            int index = i + 1;

            int max = 0;

            while (index > 0) {
                max = Math.max(max, data[index]);
                index -= lowbit(index);
            }

            return max;
        }

        private int lowbit(int x) {
            return x & -x;
        }
    }

}
