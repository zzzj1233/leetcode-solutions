package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-06 17:16
 */
public class Leet1494 {

    public static void main(String[] args) {

        System.out.println(minNumberOfSemesters(5, LeetUtils.convertInts("[[3,1]]"), 4));

        System.out.println(minNumberOfSemesters(4, LeetUtils.convertInts("[[2,1],[3,1],[1,4]]"), 2));

        System.out.println(minNumberOfSemesters(5, LeetUtils.convertInts("[[2,1],[3,1],[4,1],[1,5]]"), 2));

        System.out.println(minNumberOfSemesters(15, LeetUtils.convertInts("[[12,11]]"), 12));

    }

    public static int minNumberOfSemesters(int n, int[][] relations, int k) {

        int[] relay = new int[n];

        for (int[] relation : relations)
            relay[relation[1] - 1] |= 1 << (relation[0] - 1);

        int limit = 1 << n;

        int[] f = new int[limit];

        Arrays.fill(f, Integer.MAX_VALUE);

        f[0] = 0;

        for (int stat = 0; stat < limit; stat++) {

            int r = 0;

            for (int j = 0; j < n; j++)
                if ((stat & (1 << j)) != 0)
                    r |= relay[j];

            if (f[r] == Integer.MAX_VALUE)
                continue;

            int xor = stat ^ r;

            int cnt = Integer.bitCount(xor);

            if (cnt <= k)
                f[stat] = Math.min(f[stat], f[r] + 1);
            else for (int j = xor; j > 0; j = (j - 1) & xor)
                if (Integer.bitCount(j) == k)
                    f[stat] = Math.min(f[stat], f[stat ^ j] + 1);

        }

        return f[limit - 1];
    }


}
