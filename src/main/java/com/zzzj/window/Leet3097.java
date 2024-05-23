package com.zzzj.window;

/**
 * @author zzzj
 * @create 2024-04-01 12:01
 */
public class Leet3097 {

    public static void main(String[] args) {

        System.out.println(minimumSubarrayLength(new int[]{1, 2, 3}, 2));

        System.out.println(minimumSubarrayLength(new int[]{2, 1, 8}, 10));

        System.out.println(minimumSubarrayLength(new int[]{1, 2}, 0));

    }

    public static int minimumSubarrayLength(int[] nums, int k) {

        int[] tab = new int[32];

        int N = nums.length;

        int left = 0;

        int right = 0;

        int win = 0;

        int ans = Integer.MAX_VALUE;

        while (right < N) {

            int num = nums[right];

            for (int i = 0; i < 32; i++)
                if ((num & (1 << i)) != 0)
                    if (tab[i]++ == 0)
                        win |= (1 << i);

            while (win >= k && left <= right) {
                ans = Math.min(ans, right - left + 1);
                num = nums[left++];
                for (int i = 0; i < 32; i++)
                    if ((num & (1 << i)) != 0)
                        if (--tab[i] == 0)
                            win ^= (1 << i);
            }

            right++;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
