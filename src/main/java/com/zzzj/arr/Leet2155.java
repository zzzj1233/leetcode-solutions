package com.zzzj.arr;

import java.util.ArrayList;
import java.util.List;

public class Leet2155 {


    public static void main(String[] args) {
        System.out.println(maxScoreIndices(new int[]{0, 0, 1, 0}));
    }

    public static List<Integer> maxScoreIndices(int[] nums) {

        int N = nums.length;

        int[] leftZero = new int[N + 1];
        int[] rightOne = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            leftZero[i] = leftZero[i - 1] + (nums[i - 1] == 0 ? 1 : 0);
        }

        for (int i = N - 1; i >= 0; i--) {
            rightOne[i] = rightOne[i + 1] + (nums[i] == 1 ? 1 : 0);
        }


        List<Integer> ans = new ArrayList<>();
        int maxScore = 0;

        for (int i = 0; i <= N; i++) {
            int s = leftZero[i] + rightOne[i];
            if (s > maxScore){
                maxScore = s;
                ans.clear();
                ans.add(i);
            } else if (s == maxScore){
                ans.add(i);
            }
        }

        return ans;
    }

}
