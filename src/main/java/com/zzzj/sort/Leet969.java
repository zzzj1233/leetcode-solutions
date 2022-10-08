package com.zzzj.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-10-08 12:38
 */
public class Leet969 {

    public static void main(String[] args) {
        System.out.println(pancakeSort(new int[]{3, 2, 4, 1}));
    }

    public static List<Integer> pancakeSort(int[] arr) {

        List<Integer> ans = new ArrayList<>();

        // 1 2 3

        int N = arr.length;

        for (int i = N - 1; i >= 0; i--) {

            if (arr[i] != i + 1) {
                // 使 arr[0] = i + 1
                int index = -1;

                for (int j = 0; j < i; j++) {
                    if (arr[j] == i + 1) {
                        index = j;
                        break;
                    }
                }
                reverse(arr, 0, index);
                ans.add(index + 1);
                reverse(arr, 0, i);
                ans.add(i + 1);
            }
        }

        return ans;
    }


    public static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

}
