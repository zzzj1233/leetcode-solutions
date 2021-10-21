package com.zzzj.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzzj
 * @create 2021-09-22 15:47
 */
public class Leet18 {

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{2, 2, 2, 2, 2}, 8));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int numI = nums[i];
            if (i > 0 && nums[i - 1] == numI) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && j < nums.length && nums[j] == nums[j - 1]) {
                    continue;
                }
                int numJ = nums[j];
                int sub = target - (numI + numJ);
                int k = j + 1;
                int v = nums.length - 1;

                while (k < v) {
                    int numK = nums[k];
                    int numV = nums[v];

                    if (numK + numV < sub) {
                        k++;
                    } else if (numK + numV > sub) {
                        v--;
                    } else {
                        result.add(Arrays.asList(numI, numJ, numK, numV));
                        k++;
                        v--;

                        while (k < v && nums[k] == numK) k++;
                        while (k < v && nums[v] == numV) v--;
                    }
                }
            }
        }


        return result;
    }

}
