package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-27 19:06
 */
public class Leet2055 {


    public static void main(String[] args) {
        System.out.println(Arrays.toString(platesBetweenCandles("**|**|***|", LeetUtils.convertInts("[[2,5],[5,9]]"))));
        System.out.println(Arrays.toString(platesBetweenCandles("***|**|*****|**||**|*", LeetUtils.convertInts("[[1,17],[4,5],[14,17],[5,11],[15,16]]"))));
    }

    public static int[] platesBetweenCandles(String s, int[][] queries) {

        int N = s.length();

        int[] pillar = new int[N];

        pillar[0] = s.charAt(0) == '|' ? 1 : 0;

        for (int i = 1; i < N; i++) {
            pillar[i] = pillar[i - 1] + (s.charAt(i) == '|' ? 1 : 0);
        }

        int M = queries.length;

        int[] ans = new int[M];

        int[] l = new int[N + 1];
        int[] r = new int[N];

        for (int i = N - 1; i >= 0; i--) {
            l[i] = s.charAt(i) == '|' ? i : l[i + 1];
        }

        for (int i = 0; i < N; i++) {
            r[i] = s.charAt(i) == '|' ? i : (i - 1 >= 0 ? r[i - 1] : 0);
        }

        for (int i = 0; i < M; i++) {
            int[] query = queries[i];

            int start = query[0];

            int end = query[1];

            int count = pillar[end] - (start - 1 >= 0 ? pillar[start - 1] : 0);

            if (count <= 1) {
                continue;
            }

            int left = l[start];
            int right = r[end];

            // 3. l ~ right有多少个柱子?
            int c2 = pillar[right] - (left - 1 >= 0 ? pillar[left - 1] : 0);

            ans[i] = right - left + 1 - c2;
        }


        return ans;
    }


}
