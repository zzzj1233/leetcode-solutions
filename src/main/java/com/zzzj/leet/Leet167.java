package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-07 16:38
 */
public class Leet167 {

    public static int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;

        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            } else if (sum > target) {
                if (l == 0 || numbers[l] - numbers[l - 1] > numbers[r] - numbers[r - 1])
                    r--;
                else
                    l--;
            } else {
                if (r == numbers.length - 1 || numbers[r + 1] - numbers[r] < numbers[l + 1] - numbers[l])
                    l++;
                else
                    r++;
            }
        }

        return null;
    }


}
