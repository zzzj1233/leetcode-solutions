package com.zzzj.leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-05-20 11:36
 */
public class Leet525 {

    public static void main(String[] args) {
//        System.out.println(findMaxLength(new int[]{1, 0, 0, 1, 1, 1, 0, 1, 0, 1}));
//        System.exit(0);
        for (int i = 0; i < 10000; i++) {
            int[] ints = LeetUtils.randomBinaryArr(10);
            if (findMaxLength(ints) != right(ints)) {
                System.out.println(Arrays.toString(ints));
                System.out.println("Error");
                return;
            }
        }
    }

    public static int findMaxLength(int[] nums) {
        int N = nums.length;
        int[] preSum = new int[N + 1];

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            preSum[i] = (nums[i - 1] == 0 ? -1 : 1) + preSum[i - 1];
            map.put(preSum[i], i);
        }

        int ans = 0;

        for (int i = 0; i <= nums.length; i++) {
            ans = Math.max(map.getOrDefault(preSum[i], -1) - i, ans);
        }

        return ans;
    }


    public static int right(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                int prevIndex = map.get(counter);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(counter, i);
            }
        }
        return maxLength;
    }

}
