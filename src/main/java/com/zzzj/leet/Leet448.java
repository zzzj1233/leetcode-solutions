package com.zzzj.leet;

import com.zzzj.util.Unresolved;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-05-22 19:10
 */
public class Leet448 {

    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            // swap
            if (nums[i] != i + 1) {
                while (true) {
                    if (nums[i] == i + 1) {
                        break;
                    }
                    if (nums[nums[i] - 1] == nums[i]) {
                        break;
                    }
                    swap(nums, i, nums[i] - 1);
                }
            }

        }

        List<Integer> ans = new ArrayList<>(2);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }

        return ans;
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
