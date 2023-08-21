package com.zzzj.bit;

/**
 * @author zzzj
 * @create 2023-06-09 11:41
 */
public class Leet2680 {

    public static void main(String[] args) {

        System.out.println(maximumOr(new int[]{12, 9}, 1));

        System.out.println(maximumOr(new int[]{8, 1, 2}, 2));

        // [88,43,61,72,13]
        // 6
        System.out.println(maximumOr(new int[]{88, 43, 61, 72, 13}, 6));

    }

    public static long maximumOr(int[] nums, int k) {

        // 1100
        // 1001
        // 10010
        //  1100
        // 11110

        int[] bucket = new int[32];

        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0)
                    bucket[i]++;
            }
        }

        long ans = 0;

        for (int num : nums) {

            // 把num左移k位
            long stat = 0;

            for (int i = 0; i < 32; i++) {

                if ((num & (1 << i)) != 0) {
                    stat |= 1L << (i + k);
                    if (bucket[i] > 1)
                        stat |= 1 << i;
                } else if (bucket[i] > 0)
                    stat |= 1 << i;
            }

            ans = Math.max(ans, stat);
        }

        return ans;
    }

}
