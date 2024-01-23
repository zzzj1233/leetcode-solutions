package com.zzzj.graph;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-01-10 14:42
 */
public class Leet1601 {

    public static void main(String[] args) {

        System.out.println(maximumRequests(5, LeetUtils.convertInts("[[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]]")));

        System.out.println(maximumRequests(3, LeetUtils.convertInts("[[0,0],[1,2],[2,1]]")));

        System.out.println(maximumRequests(4, LeetUtils.convertInts("[[0,3],[3,1],[1,2],[2,0]]")));

    }

    public static int maximumRequests(int n, int[][] requests) {

        int N = requests.length;

        int limit = 1 << N;

        int ans = 0;

        for (int s = 0; s < limit; s++) {
            if (check(n, requests, s))
                ans = Math.max(ans, Integer.bitCount(s));
        }

        return ans;
    }

    public static boolean check(int n, int[][] requests, int s) {

        int[] f = new int[n];

        for (int i = 0; i < requests.length; i++) {
            if ((s & (1 << i)) != 0) {
                f[requests[i][0]]--;
                f[requests[i][1]]++;
            }
        }

        return Arrays.stream(f).allMatch(value -> value == 0);
    }
}
