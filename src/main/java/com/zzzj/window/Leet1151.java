package com.zzzj.window;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-02-19 22:18
 */
public class Leet1151 {

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int[] arr = LeetUtils.randomBinaryArr(50);

            if (minSwaps(arr) != right(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(minSwaps(arr));
                return;
            }

        }

    }

    public static int minSwaps(int[] data) {

        int count = 0;

        for (int i = 0; i < data.length; i++) {
            if (data[i] == 1) {
                count++;
            }
        }

        int l = 0;
        int r = count;

        int min = count;

        for (int i = 0; i < r; i++) {
            if (data[i] == 1) {
                min--;
            }
        }

        int ans = min;

        while (r < data.length) {
            int datum = data[r];
            if (data[l] == 1) {
                min += datum == 1 ? 0 : 1;
            } else {
                min += datum == 1 ? -1 : 0;
            }
            ans = Math.min(ans, min);
            r++;
            l++;
        }

        return ans;
    }

    public static int right(int[] data) {
        if (data == null || data.length == 0) return 0;
        int N = data.length;

        int ones = 0;
        for (int d : data)
            if (d == 1) ones++;

        int fixLen = ones; // 固定窗口的长度
        int minZeros = Integer.MAX_VALUE, curZeros = 0, left = 0;
        for (int i = 0; i < N; i++) {

            // 右边界
            if (data[i] == 0) curZeros++;

            // 左边界
            if (i - left + 1 > fixLen) {
                if (data[left] == 0) curZeros--;
                left++;
            }
            // 更新结果
            if (i - left + 1 == fixLen)
                minZeros = Math.min(minZeros, curZeros);
        }
        return minZeros == Integer.MAX_VALUE ? 0 : minZeros;
    }


}
