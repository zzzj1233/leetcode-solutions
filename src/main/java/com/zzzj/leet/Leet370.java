package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2022-05-13 10:56
 */
public class Leet370 {

    // 5
    // [[1,3,2],[2,4,3],[0,2,-2]]
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];

        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int add = update[2];

            ans[start] += add;
            if (end + 1 < length) {
                ans[end + 1] -= add;
            }
        }

        for (int i = 1; i < ans.length; i++) {
            ans[i] += ans[i - 1];
        }

        return ans;
    }

}
