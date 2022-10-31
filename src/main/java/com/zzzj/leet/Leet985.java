package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-09-25 18:49
 */
public class Leet985 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sumEvenAfterQueries(new int[]{1, 2, 3, 4}, LeetUtils.convertInts("[[1,0],[-3,1],[-4,0],[2,3]]"))));
    }

    public static int[] sumEvenAfterQueries(int[] nums, int[][] queries) {

        int oddSum = 0;

        for (int num : nums) {
            if (num % 2 == 0) {
                oddSum += num;
            }
        }

        int N = queries.length;

        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            int[] query = queries[i];

            int add = query[0];

            int index = query[1];

            boolean isOdd = nums[index] % 2 == 0;

            int origin = nums[index];

            nums[index] += add;

            if (nums[index] % 2 == 0) {
                if (isOdd) {
                    oddSum += add;
                } else {
                    oddSum += nums[index];
                }
            } else if (isOdd) {
                oddSum -= origin;
            }

            ans[i] = oddSum;
        }

        return ans;
    }

}
