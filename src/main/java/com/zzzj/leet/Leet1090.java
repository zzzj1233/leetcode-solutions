package com.zzzj.leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzzj
 * @create 2022-09-07 20:37
 */
public class Leet1090 {

    public static void main(String[] args) {

        System.out.println(
                largestValsFromLabels(new int[]{3, 2, 3, 2, 1}, new int[]{1, 0, 2, 2, 1}, 2, 1)
        );

    }

    public static int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int N = values.length;

        int[][] combined = new int[N][2];

        for (int i = 0; i < N; i++) {
            combined[i] = new int[]{values[i], labels[i]};
        }

        Arrays.sort(combined, (o1, o2) -> o2[0] - o1[0]);

        Map<Integer, Integer> labelCnt = new HashMap<>();

        int ans = 0;

        int cnt = 0;

        for (int i = 0; i < N && cnt < numWanted; i++) {
            int val = combined[i][0];
            int lab = combined[i][1];

            Integer old = labelCnt.getOrDefault(lab, 0);

            if (old == useLimit) {
                continue;
            }

            labelCnt.put(lab, old + 1);
            ans += val;
            cnt++;
        }

        return ans;
    }

}
