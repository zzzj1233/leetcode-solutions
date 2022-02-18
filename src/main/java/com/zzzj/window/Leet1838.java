package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-01-17 15:45
 */
public class Leet1838 {


    public static void main(String[] args) {
        int[] arr = ArrayUtil.generateArray(300, 1, 300);
        int k = LeetUtils.random.nextInt(300);

        final int perhaps = maxFrequency(arr, k);

        System.out.println(Arrays.toString(arr));
        System.out.println(k);
        System.out.println(perhaps);
    }


    // Note that you can try all values in a brute force manner and find the maximum frequency of that value.
    // To find the maximum frequency of a value consider the biggest elements smaller than or equal to this value
    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);

        int l = 0;
        int r = 0;
        int ans = 0;

        int curSum = 0;

        while (r < nums.length) {
            // r一定是当前窗口的最大值
            curSum += nums[r];

            int sub = curSum + k;

            while (sub / nums[r] < r - l + 1) {
                // 缩短窗口
                curSum -= nums[l];
                l++;
                sub = curSum + k;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }


}
