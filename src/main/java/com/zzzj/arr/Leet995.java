package com.zzzj.arr;

import java.util.BitSet;

/**
 * @author zzzj
 * @create 2023-11-02 12:10
 */
public class Leet995 {

    public static void main(String[] args) {

        System.out.println(minKBitFlips(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3));

    }

    public static int minKBitFlips(int[] nums, int k) {

        int N = nums.length;

        BitSet mask = new BitSet(N);

        int ans = 0;

        for (int i = 0; i < N; i++) {

            // 反转右边k个
            if ((nums[i] ^ (mask.get(i) ? 1 : 0)) == 0) {
                if (i + k > N)
                    return -1;
                mask.flip(i, i + k);
                ans++;
            }

        }

        return ans;
    }

}
