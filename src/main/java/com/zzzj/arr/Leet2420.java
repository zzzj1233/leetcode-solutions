package com.zzzj.arr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzzj
 * @create 2023-08-07 17:09
 */
public class Leet2420 {

    public static void main(String[] args) {

        System.out.println(goodIndices(new int[]{2, 1, 1, 1, 3, 4, 1}, 2));

        System.out.println(goodIndices(new int[]{2, 1, 1, 2}, 2));

    }

    public static List<Integer> goodIndices(int[] nums, int k) {

        int N = nums.length;

        int[] prefix = new int[N];

        int[] suffix = new int[N];

        prefix[0] = 1;

        for (int i = 1; i < N; i++) {
            if (nums[i] >= nums[i - 1])
                prefix[i] = prefix[i - 1] + 1;
            else
                prefix[i] = 1;
        }

        suffix[N - 1] = 1;

        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] <= nums[i + 1])
                suffix[i] = suffix[i + 1] + 1;
            else
                suffix[i] = 1;
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 1; i < N - 1; i++) {

            if (prefix[i - 1] >= k && suffix[i + 1] >= k)
                ans.add(i);

        }

        return ans;
    }

}
