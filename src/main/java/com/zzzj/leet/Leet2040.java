package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2024-06-06 16:04
 */
public class Leet2040 {

    public static void main(String[] args) {

        System.out.println(kthSmallestProduct(
                new int[]{-2, -1, 0, 1, 2},
                new int[]{-3, -1, 2, 4, 5},
                3
        ));

    }

    public static long kthSmallestProduct(int[] nums1, int[] nums2, long k) {

        int N = nums1.length;

        int M = nums2.length;

        long l = Math.min(
                Math.min(
                        (long) nums1[0] * nums2[0],
                        (long) nums1[N - 1] * nums2[0]
                ),
                Math.min(
                        (long) nums1[0] * nums2[M - 1],
                        (long) nums1[N - 1] * nums2[M - 1]
                )
        ) - 1;

        long r = Math.max(
                Math.max(
                        (long) nums1[0] * nums2[0],
                        (long) nums1[N - 1] * nums2[0]
                ),
                Math.max(
                        (long) nums1[0] * nums2[M - 1],
                        (long) nums1[N - 1] * nums2[M - 1]
                )
        );

        while (l + 1 < r) {

            long m = l + ((r - l) >> 1);

            if (check(nums1, nums2, k, m))
                r = m;
            else
                l = m;

        }

        return r;
    }

    public static boolean check(
            int[] nums1,
            int[] nums2,
            long k,
            long exp
    ) {

        int N = nums1.length;

        int M = nums2.length;

        long cnt = 0;

        long largest;

        for (int i = 0; i < N && cnt < k; i++) {

            if (nums1[i] < 0)
                largest = (long) nums1[i] * nums2[0];
            else
                largest = (long) nums1[i] * nums2[M - 1];

            if (exp >= largest)
                cnt += M;
            else
                cnt += search(nums1[i], nums2, exp); // 二分

        }

        return cnt >= k;
    }

    public static int search(
            long num1,
            int[] nums2,
            long exp
    ) {

        int l = 0;

        int r = nums2.length - 1;

        while (l <= r) {

            int m = l + ((r - l) >> 1);

            if (num1 * nums2[m] <= exp) {
                if (num1 < 0)
                    r = m - 1;
                else
                    l = m + 1;
            } else {
                if (num1 < 0)
                    l = m + 1;
                else
                    r = m - 1;
            }
        }

        return num1 < 0 ? Math.max(0, (nums2.length - r - 1)) : l;
    }

}
