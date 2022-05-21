package com.zzzj.daily;

import com.zzzj.leet.LeetUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author Zzzj
 * @create 2022-05-08 21:49
 */
public class Leet442 {

    public static void main(String[] args) {
//        System.out.println(findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
//        System.out.println(findDuplicates(new int[]{1, 1, 2}));
//        System.out.println(findDuplicates(new int[]{1}));

        // 每个数最多出现两次,最少一次
        // 1 ~ N的数
        int N = 1000;

        for (int k = 0; k < 10000; k++) {
            int[] bucket = new int[N + 1];

            int extra = LeetUtils.random.nextInt(N);

            int[] nums = new int[N + extra + 1];

            for (int i = 0; i < nums.length; i++) {
                nums[i] = i + 1;
                if (extra > 0 && LeetUtils.random.nextInt() % 2 == 0 && i + 1 < nums.length) {
                    nums[i + 1] = i + 1;
                    i++;
                    extra--;
                }
            }

            LeetUtils.shuffle(nums);

            int[] origin = Arrays.copyOfRange(nums, 0, nums.length);
            int[] origin2 = Arrays.copyOfRange(origin, 0, origin.length);

            List<Integer> duplicates = null;
            try {
                duplicates = findDuplicates(origin);
            } catch (Exception e) {
                System.out.println(Arrays.toString(origin2));
                return;
            }

            if (right(nums).size() != duplicates.size()) {
                System.out.println(Arrays.toString(origin2));
                System.out.println(findDuplicates(origin2));
                return;
            }
        }

    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static List<Integer> right(int[] nums) {
        List<Integer> ans = new ArrayList<>(nums.length);

        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                ans.add(nums[i]);
            }
        }

        return ans;
    }

    public static List<Integer> findDuplicates(int[] nums) {
        int N = nums.length;

        // 数字全都在1 ~ N范围上
        int i = 0;
        int j = nums.length - 1;

        List<Integer> ans = new ArrayList<>();

        while (i <= j) {
            // swap
            if (nums[i] == -1) {
                i++;
            } else if (nums[i] != i + 1) {
                // 这个数字可以丢掉
                if (nums[nums[i] - 1] == nums[i]) {
                    ans.add(nums[i]);
                    nums[i] = -1;
                    swap(nums, i, j);
                    j--;
                } else {
                    swap(nums, i, nums[i] - 1);
                }
            } else {
                i++;
            }
        }

        return ans;
    }

}
