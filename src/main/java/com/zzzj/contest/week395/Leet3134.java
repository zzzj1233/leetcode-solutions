package com.zzzj.contest.week395;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-06-06 11:04
 */
public class Leet3134 {

    public static void main(String[] args) {

        System.out.println(medianOfUniquenessArray(LeetUtils.convertInts1("[91,64,76,18,61,55,46,93,65,99]")));

        System.out.println(medianOfUniquenessArray(new int[]{4, 3, 5, 4}));

        System.out.println(medianOfUniquenessArray(new int[]{3, 4, 3, 3}));

    }

    public static int medianOfUniquenessArray(int[] nums) {

        int N = nums.length;

        int l = 0;

        int r = N;

        int[] tab = new int[(int) (1e5 + 1)];

        while (l + 1 < r) {

            int m = l + ((r - l) >> 1);

            if (check(nums, m, tab))
                r = m;
            else
                l = m;

            Arrays.fill(tab, 0);
        }

        return r;
    }

    public static boolean check(
            int[] nums,
            int exp,
            int[] tab
    ) {

        int l = 0;

        int r = 0;

        int N = nums.length;

        int diff = 0;

        long cnt = 0;

        while (r < N) {

            if (tab[nums[r]]++ == 0)
                diff++;

            while (diff > exp) {
                if (--tab[nums[l]] == 0)
                    diff--;
                l++;
            }

            cnt += r - l + 1;

            r++;
        }

        long total = ((long) N * (N + 1)) / 2;

        return total % 2 == 0 ? cnt >= total / 2 : cnt > total / 2;
    }

}
