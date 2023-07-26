package com.zzzj.contest.week354;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leet6927 {

    public static void main(String[] args) {

        System.out.println(minimumIndex(Arrays.asList(1, 2, 2, 2)));

        System.out.println(minimumIndex(Arrays.asList(2, 1, 3, 1, 1, 1, 7, 1, 2, 1)));

        System.out.println(minimumIndex(Arrays.asList(3, 3, 3, 3, 7, 2, 2)));

    }

    public static int minimumIndex(List<Integer> nums) {


        // 1. 找到支配元素

        Map<Integer, Integer> map = new HashMap<>();

        int maxCnt = 0;

        int ele = -1;

        for (Integer num : nums) {

            int cnt = map.getOrDefault(num, 0) + 1;

            if (cnt > maxCnt) {
                ele = num;
                maxCnt = cnt;
            }

            map.put(num, cnt);
        }

        // 2. 遍历每一个下标

        int leftCnt = 0;

        int rightCnt = maxCnt;

        int N = nums.size();

        for (int i = 0; i < N; i++) {

            if (nums.get(i) == ele) {
                leftCnt++;
                rightCnt--;
            }

            int leftLength = i + 1;

            int rightLength = N - i - 1;

            if (leftCnt << 1 > leftLength && rightCnt << 1 > rightLength) return i;

        }

        return -1;
    }

}
