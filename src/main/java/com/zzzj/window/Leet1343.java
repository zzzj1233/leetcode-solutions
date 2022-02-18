package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-20 17:56
 */
public class Leet1343 {

    public static void main(String[] args) {
        final int[] arr = ArrayUtil.generateArray(10, 1, 100);
        final int k = LeetUtils.random.nextInt(arr.length);
        final int threshold = LeetUtils.random.nextInt(arr.length);

        System.out.println(Arrays.toString(arr));
        System.out.println(k);
        System.out.println(threshold);

        System.out.println(numOfSubarrays(arr, k, threshold));
    }

    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int[] sum = new int[arr.length + 1];

        for (int i = 1; i <= arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }


        int l = 0;

        int ans = 0;

        for (int r = k; r <= arr.length; r++, l++) {
            int sub = sum[r] - sum[l];
            if (sub / k >= threshold) {
                ans++;
            }
        }

        return ans;
    }

}
