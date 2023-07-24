package com.zzzj.contest.week352;

import javafx.scene.AmbientLight;

import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2023-07-18 14:24
 */
public class Leet2762 {

    public static void main(String[] args) {

        System.out.println(continuousSubarrays(new int[]{5, 4, 2, 4}));

        System.out.println(continuousSubarrays(new int[]{1, 2, 3}));

    }

    public static long continuousSubarrays(int[] nums) {

        int N = nums.length;

        long ans = 0;

        int left = 0;

        int right = 0;

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        while (right < N) {

            int num = nums[right];

            treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);

            while (treeMap.lastKey() - treeMap.firstKey() > 2) {

                int leftNum = nums[left];

                Integer oldCnt = treeMap.get(leftNum);

                if (oldCnt == 1) treeMap.remove(leftNum);
                else treeMap.put(leftNum, oldCnt - 1);

                left++;
            }

            ans += right - left + 1;

            right++;
        }

        return ans;
    }

}
