package com.zzzj.prefxisum;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-12-09 14:42
 */
public class Leet1310 {

    /**
     * 有一个正整数数组arr，现给你一个对应的查询数组queries，其中queries[i] = [Li,Ri]。
     * <p>
     * 对于每个查询i，请你计算从Li到Ri的XOR值（即arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
     * <p>
     * 并返回一个包含给定查询queries所有结果的数组。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
     * 输出：[2,7,14,8]
     * 解释：
     * 数组中元素的二进制表示形式是：
     * 1 = 0001
     * 3 = 0011
     * 4 = 0100
     * 8 = 1000
     * 查询的 XOR 值为：
     * [0,1] = 1 xor 3 = 2
     * [1,2] = 3 xor 4 = 7
     * [0,3] = 1 xor 3 xor 4 xor 8 = 14
     * [3,3] = 8
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(xorQueries(new int[]{1, 3, 4, 8}, new int[][]{{0, 1}, {1, 2}, {0, 3}, {3, 3}})));
    }

    public static int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;

        int[] L = new int[n];
        int[] R = new int[n];

        L[0] = arr[0];
        R[n - 1] = arr[n - 1];

        for (int i = 1; i < n; i++) {
            L[i] = arr[i] ^ L[i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            R[i] = arr[i] ^ R[i + 1];
        }

        // [1, 2, 6, 14]
        // [14, 15, 12, 8]
        System.out.println(Arrays.toString(L));
        System.out.println(Arrays.toString(R));

        int sum = L[n - 1];

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];

            int end = queries[i][1];

            int l = 0;

            if (start - 1 >= 0) {
                l = L[start - 1];
            }

            int r = 0;

            if (end + 1 < queries.length) {
                r = R[end + 1];
            }

            answer[i] = sum ^ (l ^ r);
        }

        return answer;
    }

}
