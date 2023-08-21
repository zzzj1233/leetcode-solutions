package com.zzzj.contest.dweek94;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2023-08-10 18:16
 */
public class Leet2528 {

    public static void main(String[] args) {
//
//        System.out.println(maxPower(new int[]{1, 2, 4, 5, 0}, 1, 2));

        System.out.println(maxPower(new int[]{4, 4, 4, 4}, 0, 3));

//        System.out.println(maxPower(new int[]{4, 2}, 1, 1));

    }

    public static long maxPower(int[] stations, int r, int k) {

        long left = 0;

        long right = Arrays.stream(stations).asLongStream().sum() + k;

        long ans = 0;

        while (left <= right) {
            long mid = left + ((right - left) >> 1);

            if (check(stations, r, k, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return ans;
    }

    public static boolean check(int[] stations, int r, int k, long expect) {

        long sum = 0;

        int right = r;

        for (int i = 0; i < right; i++) {
            sum += stations[i];
        }

        Map<Integer, Long> diff = new HashMap<>();

        for (int i = 0; i < stations.length; i++, right++) {

            if (right < stations.length)
                sum += stations[right];

            if (diff.containsKey(i))
                sum += diff.get(i);

            if (sum < expect) {

                long sub = expect - sum;

                diff.put(i + 1 + r, sub);

                k -= sub;
            }

            if (k < 0)
                return false;

            sum -= stations[0];
        }

        return true;
    }


}
