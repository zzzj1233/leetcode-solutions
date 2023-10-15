package com.zzzj.dp;

import com.zzzj.util.ArrayCopyIterator;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

public class Leet1537 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {

            int M = 1000;

            int[] nums1 = Arrays.stream(ArrayUtil.generateArray(M)).distinct().sorted().toArray();

            int[] nums2 = Arrays.stream(ArrayUtil.generateArray(M)).distinct().sorted().toArray();

            ArrayCopyIterator it1 = ArrayCopyIterator.fromArray(nums1);
            ArrayCopyIterator it2 = ArrayCopyIterator.fromArray(nums2);

            int r = maxSum(it1.next(), it2.next());

            int rr = right(it1.next(), it2.next());

            if (r != rr) {
                System.out.println("Error");
                return;
            }
        }

        System.out.println("Ok");
    }

    public static int x;

    public static int y;
    public static int[] m1;
    public static int[] m2;

    public static int[] n1;

    public static int[] n2;

    public static long[] dp1;

    public static long[] dp2;

    static final int MOD = 1000000007;

    public static int maxSum(int[] nums1, int[] nums2) {

        int N = nums1.length;

        int M = nums2.length;

        x = 1;
        y = 1;
        dp1 = new long[N + 1];
        m1 = new int[N + 1];
        dp2 = new long[M + 1];
        m2 = new int[M + 1];
        n1 = nums1;
        n2 = nums2;

        for (int i = 0; i < N; i++)
            m1[i] = Arrays.binarySearch(nums2, nums1[i]);

        for (int i = 0; i < M; i++)
            m2[i] = Arrays.binarySearch(nums1, nums2[i]);

        calculate();

        help(Integer.MIN_VALUE);

        return (int) (Math.max(dp1[N], dp2[M])) % MOD;
    }

    public static void calculate() {

        while (x < dp1.length) {

            long num = n1[x - 1];

            int map = m1[x - 1];

            if (map >= 0) {
                if (dp2[map] == 0)
                    help(num);

                dp1[x] = dp2[map];
            }

            dp1[x] = Math.max(dp1[x], dp1[x - 1]) + num;
            x++;
        }

    }

    public static void help(long limit) {
        while (y < dp2.length) {

            long num = n2[y - 1];

            int map = m2[y - 1];

            if (map >= 0)
                dp2[y] = dp1[map];

            dp2[y] = Math.max(
                    dp2[y],
                    dp2[y - 1]
            ) + num;

            y++;

            if (num == limit)
                break;
        }
    }


    public static int right(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        long[] f = new long[n + 1], g = new long[m + 1];
        int i = 1, j = 1;
        while (i <= n || j <= m) {
            if (i <= n && j <= m) {
                if (nums1[i - 1] < nums2[j - 1]) {
                    f[i] = f[i - 1] + nums1[i - 1];
                    i++;
                } else if (nums2[j - 1] < nums1[i - 1]) {
                    g[j] = g[j - 1] + nums2[j - 1];
                    j++;
                } else {
                    f[i] = g[j] = Math.max(f[i - 1], g[j - 1]) + nums1[i - 1];
                    i++;
                    j++;
                }
            } else if (i <= n) {
                f[i] = f[i - 1] + nums1[i - 1];
                i++;
            } else {
                g[j] = g[j - 1] + nums2[j - 1];
                j++;
            }
        }
        return (int) (Math.max(f[n], g[m]) % MOD);
    }
}

