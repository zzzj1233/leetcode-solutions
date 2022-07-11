package com.zzzj.dp;

import com.zzzj.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzzj
 * @create 2022-07-06 17:41
 */
public class Leet368 {

    public static void main(String[] args) {

//        System.out.println(largestDivisibleSubset(new int[]{9, 18, 25, 28, 30, 87, 89, 90, 91}));
//
//        System.exit(0);

        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayUtil.generateArray(100, 1, 1000);
            arr = ArrayUtil.distinct(arr);
            if (largestDivisibleSubset(arr).size() != right(arr).size()) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(largestDivisibleSubset(arr));
                System.out.println(right(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int N = nums.length;

        int[] dp = new int[N];

        Arrays.fill(dp, 1);

        int max = 0;

        int index = 0;

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[j] % nums[i] == 0) {
                    dp[j] = Math.max(dp[j], 1 + dp[i]);

                    if (dp[j] > max) {
                        max = dp[j];
                        index = j;
                    }
                }

            }

        }

        LinkedList<Integer> ans = new LinkedList<>();

        ans.add(nums[index]);

        int j = index - 1;

        while (j >= 0 && ans.size() < max) {
            if (nums[index] % nums[j] == 0 && dp[j] == max - ans.size()) {
                ans.add(nums[j]);
                index = j;
            }
            j--;
        }

        return ans;
    }

    public static List<Integer> right(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) return res;
        int[] dp = new int[n];
        int numMax = 1;
        int numMaxIndex = 0;

        Arrays.fill(dp, 1);
        Arrays.sort(nums);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            if (dp[i] > numMax) {
                numMax = dp[i];
                numMaxIndex = i;
            }
        }
        for (int i = numMaxIndex; i >= 0; i--) {
            if (nums[numMaxIndex] % nums[i] == 0 && dp[i] == numMax) {
                res.add(nums[i]);
                numMax--;
            }
        }
        return res;
    }

}
