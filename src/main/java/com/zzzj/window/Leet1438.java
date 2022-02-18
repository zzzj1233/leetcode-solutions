package com.zzzj.window;

import com.zzzj.leet.LeetUtils;
import com.zzzj.util.ArrayUtil;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author zzzj
 * @create 2022-01-04 16:09
 */
public class Leet1438 {

    public static void main(String[] args) {

        while (true) {
            int[] arr = ArrayUtil.generateArray(10, 1, 10);
            int limit = LeetUtils.random.nextInt(10);
            int ans = violent(arr, limit);
            int perhaps = longestSubarray(arr, limit);
            if (ans != perhaps) {
                System.out.println("ans = " + ans + " , perhaps = " + perhaps);
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("limit = " + limit);
                return;
            }
        }
    }

    private static int violent(int[] nums, int limit) {

        int ans = 1;

        for (int i = 0; i < nums.length; i++) {
            int max = nums[i];
            int min = nums[i];

            int subCount = 1;

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                } else if (nums[j] < min) {
                    min = nums[j];
                }
                if (max - min > limit) {
                    break;
                }
                subCount++;
            }

            ans = Math.max(subCount, ans);
        }
        return ans;
    }

    public static int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        int l = 0;
        int r = 1;
        int ans = 1;

        treeMap.put(nums[0], 1);

        while (r < nums.length) {
            // 缩小窗口
            treeMap.put(nums[r], treeMap.getOrDefault(nums[r], 0) + 1);
            while (l < r && treeMap.lastKey() - treeMap.firstKey() > limit) {
                Integer count = treeMap.get(nums[l]);
                if (count == 1) {
                    treeMap.remove(nums[l]);
                } else {
                    treeMap.put(nums[l], count - 1);
                }
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }

        // 只要不满足条件就缩小窗口

        return ans;
    }

}
