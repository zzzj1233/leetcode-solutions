package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-06-20 15:34
 */
public class Leet1024 {

    public static void main(String[] args) {
        System.out.println(videoStitching(LeetUtils.convertInts("[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]"), 10));

        System.out.println(videoStitching(LeetUtils.convertInts("[[0,1],[1,2]]"), 5));

        System.out.println(videoStitching(LeetUtils.convertInts("[[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]]"), 9));
        System.out.println(videoStitching(LeetUtils.convertInts("[[16,18],[16,20],[3,13],[1,18],[0,8],[5,6],[13,17],[3,17],[5,6]]"), 15));
    }

    public static int videoStitching(int[][] clips, int time) {
        int[] greedy = new int[101];

        Arrays.fill(greedy, -1);

        int max = 0;

        for (int i = 0; i < clips.length; i++) {
            int start = clips[i][0];
            int end = clips[i][1];
            if (end > greedy[start]) {
                greedy[start] = end;
            }
            max = Math.max(max, end);
        }

        if (max < time) {
            return -1;
        }

        int end = 0;

        int ans = 0;

        while (true) {
            int temp = end;

            end = greedy[end];

            ans++;

            if (end >= time) {
                return ans;
            }

            int maxEnd = 0;
            int nextEnd = -1;

            while (end > temp) {
                if (greedy[end] > maxEnd) {
                    maxEnd = greedy[end];
                    nextEnd = end;
                }
                end--;
            }

            if (maxEnd <= temp) {
                return -1;
            }

            end = nextEnd;
        }
    }


}
