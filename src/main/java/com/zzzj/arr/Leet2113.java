package com.zzzj.arr;

import cn.hutool.core.util.StrUtil;
import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

public class Leet2113 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(elementInNums(new int[]{0, 1, 2}, LeetUtils.convertInts("[[0,2],[2,0],[3,2],[5,0]]"))));
    }

    public static int[] elementInNums(int[] nums, int[][] queries) {

        int M = queries.length;

        int[] ans = new int[M];

        int N = nums.length << 1;

        for (int i = 0; i < M; i++) {
            int[] query = queries[i];
            int time = query[0];
            int index = query[1];
            int mod = time % N;
            int curSize;
            if (mod <= nums.length) {
                curSize = nums.length - mod;
            } else {
                curSize = mod - nums.length;
            }


            if (index < curSize) {
                if (mod <= nums.length) {
                    ans[i] = nums[index + mod];
                } else {
                    ans[i] = nums[index];
                }
            } else {
                ans[i] = -1;
            }
        }

        return ans;
    }

}
