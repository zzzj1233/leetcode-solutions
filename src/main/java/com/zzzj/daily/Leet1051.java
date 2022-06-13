package com.zzzj.daily;

/**
 * @author zzzj
 * @create 2022-06-13 18:28
 */
public class Leet1051 {

    public static void main(String[] args) {
        System.out.println(heightChecker(new int[]{1, 1, 4, 2, 1, 3}));
    }

    public static int heightChecker(int[] heights) {
        int[] bucket = new int[101];

        int max = 0;

        for (int i = 0; i < heights.length; i++) {
            bucket[heights[i]]++;
            max = Math.max(max, heights[i]);
        }

        int k = 1;

        int ans = 0;

        for (int i = 0; i < heights.length; ) {

            int count = bucket[k];

            for (int j = 0; j < count; j++, i++) {
                if (heights[i] != k) {
                    ans++;
                }
            }

            k++;
        }

        return ans;
    }

}
