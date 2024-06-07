package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2024-06-07 18:14
 */
public class Leet668 {

    public static void main(String[] args) {

        System.out.println(findKthNumber(3, 3, 5));

        System.out.println(findKthNumber(34, 4542, 456));

    }

    public static int findKthNumber(int m, int n, int k) {

        int l = -1;

        int r = m * n;

        while (l + 1 < r) {

            int exp = l + ((r - l) >> 1);

            if (check(m, n, k, exp))
                r = exp;
            else
                l = exp;

        }

        return r;
    }

    public static boolean check(
            int m,
            int n,
            int k,
            int exp
    ) {

        int cnt = 0;

        for (int r = 1; r <= m; r++)
            cnt += Math.min(
                    exp / r,
                    n
            );

        return cnt >= k;
    }

}
