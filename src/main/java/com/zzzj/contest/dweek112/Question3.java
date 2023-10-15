package com.zzzj.contest.dweek112;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question3 {

    public static void main(String[] args) {

        System.out.println(maxSum(Arrays.asList(2, 6, 7, 3, 1, 7), 3, 4));

        System.out.println(maxSum(Arrays.asList(5, 9, 9, 2, 4, 5, 4), 1, 3));

        System.out.println(maxSum(Arrays.asList(1, 2, 1, 2, 1, 2, 1), 3, 3));

    }

    public static long maxSum(List<Integer> nums, int m, int k) {

        Map<Integer, Integer> cnt = new HashMap<>();

        long sum = 0;

        for (int i = 0; i < k - 1; i++) {
            Integer num = nums.get(i);
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
            sum += num;
        }

        int N = nums.size();

        int L = 0;

        int R = k - 1;

        long ans = 0;

        while (R < N) {

            Integer NR = nums.get(R);

            cnt.put(NR, cnt.getOrDefault(NR, 0) + 1);

            sum += NR;

            if (cnt.size() >= m) {
                ans = Math.max(ans, sum);
            }

            Integer num = nums.get(L);

            Integer old = cnt.get(num);

            if (old == 1)
                cnt.remove(num);
            else
                cnt.put(num, old - 1);

            sum -= num;

            R++;
            L++;
        }

        return ans;
    }

}
