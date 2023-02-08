package com.zzzj.arr;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-01-28 17:49
 */
public class Leet1552 {

    public static void main(String[] args) {
        System.out.println(maxDistance(
                new int[]{1, 2, 3, 4, 7},
                3
        ));

        System.out.println(maxDistance(
                new int[]{5, 4, 3, 2, 1, 1000000000},
                2
        ));
    }

    public static int maxDistance(int[] position, int m) {

        Arrays.sort(position);

        int N = position.length;

        int right = Arrays.stream(position).max().getAsInt();

        int left = 0;

        int ans = 0;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(position, m, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    public static boolean check(int[] position, int m, int expect) {

        int prev = position[0];

        int cnt = 1;

        for (int i = 1; i < position.length; i++) {
            if (position[i] - prev >= expect) {
                cnt++;
                prev = position[i];
            }
        }

        return cnt >= m;
    }

}
