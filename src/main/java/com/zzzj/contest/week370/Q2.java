package com.zzzj.contest.week370;

import com.zzzj.leet.LeetUtils;

public class Q2 {

    public static void main(String[] args) {

        System.out.println(findChampion(3, LeetUtils.convertInts("[[0,1],[1,2]]")));

        System.out.println(findChampion(4, LeetUtils.convertInts("[[0,2],[1,3],[1,2]]")));

    }

    public static int findChampion(int n, int[][] edges) {

        int[] indegree = new int[n];

        for (int[] edge : edges) {
            indegree[edge[1]]++;
        }

        int ans = -1;

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                if (ans == -1)
                    ans = i;
                else
                    return -1;
        }

        return ans;
    }

}
