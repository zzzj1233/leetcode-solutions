package com.zzzj.leet;


/**
 * @author zzzj
 * @create 2021-12-08 11:23
 */
public class Leet1588 {

    /**
     * 输入：arr = [1,4,2,5,3]
     * 输出：58
     * 解释：所有奇数长度子数组和它们的和为：
     * [1] = 1
     * [4] = 4
     * [2] = 2
     * [5] = 5
     * [3] = 3
     * [1,4,2] = 7
     * [4,2,5] = 11
     * [2,5,3] = 10
     * [1,4,2,5,3] = 15
     * 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
     */

    public static void main(String[] args) {
        System.out.println(sumOddLengthSubarrays(new int[]{10, 11, 12}));
    }

    public static int sumOddLengthSubarrays(int[] arr) {
        int[] sum = new int[arr.length];

        sum[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        int total = 0;

        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
            for (int j = i + 2; j < arr.length; j += 2) {
                total += sum[j] - (i - 1 < 0 ? 0 : sum[i - 1]);
            }
        }

        return total;
    }

}
