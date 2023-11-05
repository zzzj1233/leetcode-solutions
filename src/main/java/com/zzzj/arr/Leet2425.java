package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2021-12-20 22:51
 */
public class Leet2425 {

    public static void main(String[] args) {
        System.out.println(smallestDifference(new int[]{-2147483648, 1}, new int[]{2147483647, 0}));
    }

    public static int smallestDifference(int[] a, int[] b) {

        Arrays.sort(a);
        Arrays.sort(b);

        int i = 0;
        int j = 0;
        int ans = Integer.MAX_VALUE;


        while (i < a.length && j < b.length) {
            int n1 = a[i];
            int n2 = b[j];

            if (n1 > n2) {
                j++;
                ans = Math.min(ans, n1 - n2);
            } else if (n1 < n2) {
                i++;
                ans = Math.min(ans, n2 - n1);
            } else {
                return 0;
            }
        }

        return ans;
    }

}
