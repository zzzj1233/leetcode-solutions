package com.zzzj.contest.dweek128;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author zzzj
 * @create 2024-05-23 18:26
 */
public class Leet3113 {

    public static void main(String[] args) {

        System.out.println(numberOfSubarrays(new int[]{1, 4, 3, 3, 2}));

        System.out.println(numberOfSubarrays(new int[]{3, 3, 3}));

    }

    public static long numberOfSubarrays(int[] nums) {

        int N = nums.length;

        LinkedList<Integer> stack = new LinkedList<>();

        long ans = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && stack.peekLast() < nums[i]) {

                Integer num = stack.removeLast();

                Integer cnt = map.get(num);

                ans += cnt;

                map.put(num, cnt - 1);
            }

            stack.add(nums[i]);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        while (!stack.isEmpty()) {
            Integer num = stack.removeLast();

            Integer cnt = map.get(num);

            ans += cnt;

            map.put(num, cnt - 1);
        }

        return ans;
    }

}
