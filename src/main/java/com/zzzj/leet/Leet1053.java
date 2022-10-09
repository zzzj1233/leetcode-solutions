package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-09-25 17:07
 */
public class Leet1053 {


    public static void main(String[] args) {
        System.out.println(Arrays.toString(prevPermOpt1(new int[]{3, 1, 1, 3})));
    }

    // [3,1,1,3] -> 1313
    public static int[] prevPermOpt1(int[] arr) {

        int N = arr.length;

        int[] min = new int[N];

        min[N - 1] = arr[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            min[i] = Math.min(arr[i], min[i + 1]);
        }

        int last = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > min[i]) {
                last = i;
            }
        }

        if (last == -1) {
            return arr;
        }

        // 和右边最大的元素换
        int maxIdx = last + 1;

        for (int i = last + 2; i < N; i++) {
            if (arr[i] != arr[last] && arr[i] > arr[maxIdx]) {
                maxIdx = i;
            }
        }


        int temp = arr[last];
        arr[last] = arr[maxIdx];
        arr[maxIdx] = temp;

        return arr;
    }


}
