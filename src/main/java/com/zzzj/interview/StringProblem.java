package com.zzzj.interview;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-02 11:35
 */
public class StringProblem {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int[] arr = ArrayUtil.generateArray(100, 0, 100);
            Arrays.sort(arr);
            int k = LeetUtils.random.nextInt(100);

            if (solution(arr, k) != maxCoverPoint(arr, k)) {
                System.out.println(Arrays.toString(arr));
                System.out.println(k);
                System.out.println("Error");
                return;
            }
        }

        System.out.println("Ok");
    }

    // 一个有序数组,一个绳子长度为K,求这个绳子能盖住的最大点数
    public static int solution(int[] arr, int k) {
        if (k == 0) {
            return 0;
        }

        // 1 3 5 9 10 11 19 21 22 27

        // k = 5

        int l = 0;
        int r = 0;

        int ans = 0;

        while (l < arr.length) {

            while (r < arr.length && arr[r] - arr[l] <= k) {
                r++;
            }

            ans = Math.max(ans, r - l);

            l++;
        }

        return ans;
    }

    public static int maxCoverPoint(int[] arr, int K) {
        //双指针 时间复杂度为O(N)
        if (arr == null || arr.length == 0 || K <= 0) {
            return 0;
        }
        int max = 1;
        int L = 0;
        int R = 0;
        for (; L < arr.length; L++) {
            while (R < arr.length && arr[R] - arr[L] <= K) {
                R++;
            }
            //R指向的是匹配失败的位置
            max = Math.max(max, R - L);
        }
        return max;
    }


}
