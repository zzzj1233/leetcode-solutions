package com.zzzj.bit;

import java.util.BitSet;

/**
 * @author zzzj
 * @create 2023-10-26 15:18
 */
public class Leet982 {

    public static void main(String[] args) {

        System.out.println(countTriplets(new int[]{2, 1, 3}));

//        System.out.println(countTriplets(new int[]{0, 0, 0}));

    }

    public static int countTriplets(int[] nums) {

        int N = nums.length;

        BitSet[] bucket = new BitSet[32];

        for (int i = 0; i < 32; i++) {
            bucket[i] = new BitSet();
        }

        for (int i = 0; i < N; i++) {

            int num = nums[i];

            for (int j = 0; j <= 31; j++)
                if ((num & (1 << j)) != 0)
                    bucket[j].set(i, true);

        }

        int ans = 0;

        BitSet set = new BitSet();

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                set.clear();

                int and = nums[i] & nums[j];

                int x = 31;

                for (; x >= 0; x--) {
                    if ((and & (1 << x)) != 0)
                        break;
                }

                for (int y = 0; y <= x; y++)
                    if ((and & (1 << y)) != 0)
                        set.or(bucket[y]);

                int res = N - set.cardinality();

                ans += res;
            }

        }

        return ans;
    }


}
