package com.zzzj.contest.week356;

import com.zzzj.graph.dfs.BinaryGraphCheck;

import java.util.Arrays;

public class Leet6900 {


    public static void main(String[] args) {

        System.out.println(countCompleteSubarrays(new int[]{1, 3, 1, 2, 2}));

        System.out.println(countCompleteSubarrays(new int[]{5, 5, 5, 5}));

    }

    public static int countCompleteSubarrays(int[] nums) {

        int N = nums.length;

        int[] visited = new int[1001];

        int cnt = 0;

        for (int num : nums) {
            if (visited[num] == 0) cnt++;
            visited[num]++;
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {

            int curCnt = 0;
            Arrays.fill(visited, 0);

            for (int j = i; j < N; j++) {

                if (visited[nums[j]] == 0) {
                    curCnt++;
                }

                visited[nums[j]]++;

                if (curCnt == cnt) ans++;

            }

        }

        return ans;
    }

}
