package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-20 19:05
 */
public class Leet484 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findPermutation("DI")));
    }

    public static int[] findPermutation(String s) {
        int[] ans = new int[s.length() + 1];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = i + 1;
        }

        for (int i = ans.length - 1; i > 0; i--) {
            char c = s.charAt(i - 1);
            //
            if (c == 'D') {
                // while i != d
                int j = i;
                while (i >= 1 && s.charAt(i - 1) == 'D') {
                    i--;
                }
                reverse(ans, i, j);
            }
        }

        return ans;
    }

    public static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
