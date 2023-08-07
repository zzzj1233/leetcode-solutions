package com.zzzj.contest.dweek104;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-01 16:32
 */
public class Leet2679 {

    public static void main(String[] args) {
        System.out.println(matrixSum(LeetUtils.convertInts("[[7,2,1],[6,4,2],[6,5,3],[3,2,1]]")));

        System.out.println(matrixSum(LeetUtils.convertInts("[[1,8,16,15,12,9,15,11,18,6,16,4,9,4],[3,19,8,17,19,4,9,3,2,10,15,17,3,11],[13,10,19,20,6,17,15,14,16,8,1,17,0,2],[12,20,0,19,15,10,7,10,2,6,18,7,7,4],[17,14,2,2,10,16,15,3,9,17,9,3,17,10],[17,6,19,17,18,9,14,2,19,12,10,18,7,9],[5,6,5,1,19,8,15,2,2,4,4,1,2,17],[12,16,8,16,7,6,18,13,18,8,14,15,20,11],[2,10,19,3,15,18,20,10,6,7,0,8,3,7],[11,5,10,13,1,3,4,7,1,18,20,17,19,2],[0,3,20,6,19,18,3,12,2,11,3,1,19,0],[6,5,3,15,6,1,0,17,13,19,3,8,2,7],[2,20,9,11,13,5,1,16,14,1,19,3,12,6]]")));
    }

    public static int matrixSum(int[][] nums) {
        int M = nums.length;

        int N = nums[0].length;

        int ans = 0;

        for (int i = 0; i < M; i++) {
            Arrays.sort(nums[i]);
        }

        for (int i = 0; i < N; i++) {

            int max = 0;

            for (int j = 0; j < M; j++) {
                max = Math.max(max, nums[j][i]);
            }

            ans += max;
        }

        return ans;
    }


}
