package com.zzzj.bit;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-04-25 16:07
 */
public class Leet2411 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(smallestSubarrays(new int[]{1, 0, 2, 1, 3})));

        System.out.println(Arrays.toString(smallestSubarrays(new int[]{4, 0, 5, 6, 3, 2})));

    }

    public static int[] smallestSubarrays(int[] nums) {

        int N = nums.length;

        int[] ans = new int[N];

        int[] leftTab = new int[32];

        int[] totalTab = new int[32];

        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if (((num >> i) & 1) == 1) {
                    totalTab[i]++;
                }
            }
        }

        int left = 0;

        int right = 0;

        int[] windowTab = new int[32];

        while (right < N) {

            int num = nums[right];

            for (int i = 0; i < 31; i++) {
                if (((num >> i) & 1) == 1) {
                    windowTab[i]++;
                }
            }

            while (tableValue(windowTab) == tableValue(leftTab, totalTab) && left <= right) {

                // left的值已经得出
                ans[left] = right - left + 1;

                // 移动左边窗口
                int leftNum = nums[left];

                for (int i = 0; i < 31; i++) {
                    if (((leftNum >> i) & 1) == 1) {
                        windowTab[i]--;
                        leftTab[i]++;
                    }
                }

                left++;
            }

            right++;
        }

        for (int i = left; i < N; i++) {
            ans[i] = N - i;
        }

        return ans;
    }

    public static int tableValue(int[] tab) {
        int value = 0;

        for (int i = 0; i < 31; i++) {

            if (tab[i] > 0) {
                value |= 1 << i;
            }
        }

        return value;
    }

    public static int tableValue(int[] leftTab, int[] totalTab) {
        int value = 0;

        for (int i = 0; i < 31; i++) {

            if (totalTab[i] - leftTab[i] > 0) {
                value |= 1 << i;
            }
        }

        return value;
    }


}
