package com.zzzj.contest.week369;

public class Q1 {

    public static void main(String[] args) {

        System.out.println(findKOr(new int[]{7, 12, 9, 8, 9, 15}, 4));

        System.out.println(findKOr(new int[]{2, 12, 1, 11, 4, 5}, 6));

        System.out.println(findKOr(new int[]{10, 8, 5, 9, 11, 6, 8}, 1));

    }

    public static int findKOr(int[] nums, int k) {

        int[] bucket = new int[32];

        for (int i = 0; i < nums.length; i++) {

            int cnt = 0;

            for (int j = 0; j < 31; j++) {

                if ((nums[i] & (1 << j)) != 0)
                    bucket[j]++;

            }

        }

        int ans = 0;

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] >= k)
                ans |= 1 << i;
        }

        return ans;
    }

}
