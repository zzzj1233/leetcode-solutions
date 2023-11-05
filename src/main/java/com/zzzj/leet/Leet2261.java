package com.zzzj.leet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2023-06-14 17:19
 */
public class Leet2261 {

    public static void main(String[] args) {

        System.out.println(countDistinct(new int[]{2, 3, 3, 2, 2}, 2, 2));

    }

    public static int countDistinct(int[] nums, int k, int p) {

        int N = nums.length;

        Set<String> set = new HashSet<>(N);

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < N; i++) {

            int cnt = 0;

            for (int j = i; j < N; j++) {

                builder.append(nums[j]).append("-");

                if (nums[j] % p == 0) cnt++;

                if (cnt > k) break;

                set.add(builder.toString());
            }

            builder.setLength(0);
        }

        return set.size();
    }

}
