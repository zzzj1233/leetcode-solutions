package com.zzzj.contest.week386;

import com.zzzj.leet.LeetUtils;

public class Q2 {

    public static void main(String[] args) {

        System.out.println(largestSquareArea(LeetUtils.convertInts("[[1,1],[2,2],[3,1]]"), LeetUtils.convertInts("[[3,3],[4,4],[6,6]]")));

        System.out.println(largestSquareArea(LeetUtils.convertInts("[[1,1],[2,2],[1,2]]"), LeetUtils.convertInts("[[3,3],[4,4],[3,4]]")));

        System.out.println(largestSquareArea(LeetUtils.convertInts("[[1,1],[3,3],[3,1]]"), LeetUtils.convertInts("[[2,2],[4,4],[4,2]]")));

    }

    public static long largestSquareArea(int[][] bottomLeft, int[][] topRight) {

        int N = bottomLeft.length;

        long ans = 0;

        for (int i = 0; i < N; i++) {

            for (int j = i + 1; j < N; j++) {

                ans = Math.max(ans, check(bottomLeft, topRight, i, j));

            }

        }

        return ans;
    }

    public static long check(int[][] bottomLeft, int[][] topRight, int i, int j) {

        int[] bli = bottomLeft[i];
        int[] blj = bottomLeft[j];

        int[] tri = topRight[i];
        int[] trj = topRight[j];

        int ib = bli[0];
        int il = bli[1];
        int it = tri[0];
        int ir = tri[1];

        int jb = blj[0];
        int jl = blj[1];
        int jt = trj[0];
        int jr = trj[1];

        long res = 0;

        int left = Math.max(il, jl);
        int right = Math.min(ir, jr);
        int top = Math.min(it, jt);
        int bottom = Math.max(ib, jb);

        long width = right - left;
        long height = top - bottom;

        if (width > 0 && height > 0) {
            return Math.min(width, height) * Math.min(width, height);
        } else {
            // 不存在交集，返回0
            return 0;
        }
    }

}

