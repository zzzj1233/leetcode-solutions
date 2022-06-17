package com.zzzj.daily;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-17 14:18
 */
public class Leet1089 {

    public static void main(String[] args) {
        duplicateZeros(new int[]{1, 0, 2, 3, 0, 4, 5, 0});
    }

    public static void duplicateZeros(int[] arr) {
        int[] copy = Arrays.copyOfRange(arr, 0, arr.length);

        int j = 0;

        int i = 0;

        while (i < arr.length) {
            arr[i] = copy[j];
            if (arr[i] == 0 && i + 1 < arr.length) {
                i++;
                arr[i] = 0;
            }
            i++;
            j++;
        }

    }

}
