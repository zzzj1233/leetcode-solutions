package com.zzzj.contest.week414;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-09-15 17:56
 */
public class Leet3281 {

    public static void main(String[] args) {

        System.out.println(maxPossibleScore(new int[]{6, 0, 3}, 2));

        System.out.println(maxPossibleScore(new int[]{2, 6, 13, 13}, 5));

    }

    public static int maxPossibleScore(int[] start, int d) {

        // [start[i], start[i] + d]

        // [ 6 - 8 ]
        // [ 0 - 2 ]
        // [ 3 - 5 ]

        // 0,1,2
        // 6,7,8
        // 3,4,5

        // 各选一个数 ( 没有负数 )
        // 使绝对差的最小值最大

        // 0 , 4 , 8

        // min( |0 - 4| , |4 - 8| , |0 - 8| ) = 4

        // 2 , 5 , 8

        // min( |2 - 5| , |5 - 8| , |2 - 8| ) = 3

        // 最小值最大 , 考虑二分
        // 如何check ?

        // 从贪心的角度来讲, 如果希望两个值(y - x)的最小值最大
        // 考虑x已选择过了 ( 最开始x = start[0][0] )
        // 那么y肯定选择 - x >= expect && 尽量小的值

        Arrays.sort(start);

        int l = -1;

        int r = start[start.length - 1] + d;

        while (l + 1 < r) {

            int m = l + ((r - l) >> 1);

            if (check(start, d, m)) {
                l = m;
            } else {
                r = m;
            }

        }

        return l;
    }

    public static boolean check(
            int[] start,
            int d,
            int expect
    ) {

        int x = start[0];

        int N = start.length;

        for (int i = 1; i < N; i++) {

            int right = start[i] + d;

            if (right - x < expect)
                return false;

            x = Math.max(start[i], x + expect);
        }

        return true;
    }

}
