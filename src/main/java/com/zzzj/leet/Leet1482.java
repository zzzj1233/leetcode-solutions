package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-09-08 10:50
 */
public class Leet1482 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(100, 1, 1000);

            int m = LeetUtils.random.nextInt(1000) + 1;

            int k = LeetUtils.random.nextInt(arr.length - 1) + 1;

            if (minDays(arr, m, k) != solution.minDays(arr, m, k)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(m);
                System.out.println(k);
                System.out.println(minDays(arr, m, k));
                System.out.println(solution.minDays(arr, m, k));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int minDays(int[] bloomDay, int m, int k) {

        int N = bloomDay.length;

        if (N < m * k) {
            return -1;
        }

        // 最慢max天
        int max = Arrays.stream(bloomDay).max().orElse(0);

        // 最快多少天?
        int min = 1;

        while (min < max) {
            int mid = min + ((max - min) >> 1);

            // 看看mid天可不可以制作M束花
            if (check(mid, bloomDay, m, k)) {
                max = mid;
            } else {
                min = mid + 1;
            }

        }

        return max;
    }

    public static boolean check(int day, int[] bloomDay, int m, int k) {
        int N = bloomDay.length;

        int count = 0;

        OUTER:
        for (int i = 0; i < N - k + 1; ) {
            if (bloomDay[i] > day) {
                i++;
                continue;
            }

            int end = i + k;

            for (int j = i + 1; j < end; j++) {
                if (bloomDay[j] > day) {
                    i = j + 1;
                    continue OUTER;
                }
            }

            count++;

            if (count == m) {
                return true;
            }

            i = end;
        }

        return count >= m;
    }

    private static
    class Solution {
        //binary
        //二分法 找到结果的范围的最大和最小，然后二分找到最终结果
        //主函数用来二分，辅助函数用来判断当前值是否满足题意
        public int minDays(int[] bloomDay, int m, int k) {
            if (m * k > bloomDay.length) return -1;
            int l = Arrays.stream(bloomDay).min().getAsInt();
            int r = Arrays.stream(bloomDay).max().getAsInt();
            while (l < r) {
                int mid = (l + r) >> 1;
                if (finished(bloomDay, m, k, mid)) r = mid;
                else l = mid + 1;
            }
            return l;
        }

        public boolean finished(int[] b, int m, int k, int res) {
            int len = b.length;
            int flowers = 0;
            int count = 0;
            for (int i = 0; i < len; i++) {
                if (b[i] > res) count = 0;
                else {
                    if (++count == k) {
                        if (++flowers == m) {
                            return true;
                        }
                        count = 0;
                    }
                }
            }
            return false;
        }
    }


}
