package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-07 19:16
 */
public class Leet1769 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(minOperations("110")));
    }

    public static int[] minOperations(String boxes) {

        int N = boxes.length();

        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            int count = 0;

            for (int j = 0; j < i; j++) {
                if (boxes.charAt(j) == '1') {
                    count += i - j;
                }
            }

            for (int j = i + 1; j < N; j++) {
                if (boxes.charAt(j) == '1') {
                    count += j - i;
                }
            }

            ans[i] = count;
        }

        return ans;
    }

}
