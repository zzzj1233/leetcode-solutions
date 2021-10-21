package com.zzzj.leet;

import java.util.*;

/**
 * @author zzzj
 * @create 2021-09-22 14:13
 */
public class Leet15 {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-2, 0, 0, 2, 2}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i > 0 && num == nums[i - 1]) {
                continue;
            }
            int sub = -num;
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int numJ = nums[j];
                int numK = nums[k];

                if (numJ + numK < sub) {
                    j++;
                } else if (numJ + numK > sub) {
                    k--;
                } else {
                    result.add(Arrays.asList(num, numJ, numK));
                    j++;
                    k--;
                    while (j < k && nums[j] == numJ) j++;
                    while (k > j && nums[k] == numK) k--;
                }
            }
        }

        return result;
    }

}
