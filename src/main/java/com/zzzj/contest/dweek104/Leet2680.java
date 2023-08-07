package com.zzzj.contest.dweek104;


/**
 * @author zzzj
 * @create 2023-08-01 16:40
 */
public class Leet2680 {


    public static void main(String[] args) {

        System.out.println(maximumOr(new int[]{12, 9}, 1));

        System.out.println(maximumOr(new int[]{8, 1, 2}, 2));

        System.out.println(maximumOr(new int[]{8, 1, 2}, 2));

    }

    public static long maximumOr(int[] nums, int k) {

        int N = nums.length;

        long xor = 0;

        int[] tab = new int[32];

        for (int num : nums) {
            xor |= num;

            for (int i = 0; i <= 31; i++) {
                if ((num & (1 << i)) != 0)
                    tab[i]++;
            }

        }

        long ans = 0;

        for (int num : nums) {

            long mul = (long) num << k;

            long cp = xor;

            for (int i = 0; i <= 31; i++) {

                if ((num & (1 << i)) != 0 && tab[i] == 1) {
                    cp ^= 1 << i;
                }

            }

            ans = Math.max(ans, cp | mul);
        }

        return ans;
    }

}
