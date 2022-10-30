package com.zzzj.arr;

import java.util.Arrays;

public class Leet2064 {


    public static void main(String[] args) {
        System.out.println(minimizedMaximum(6, new int[]{11, 6}));
    }

    public static int minimizedMaximum(int n, int[] quantities) {
        int l = 1, r = 100005;
        while (l < r) {
            int mid = (l + r) >> 1;
            int count = 0;
            for (int x : quantities) {
                count += (x - 1) / mid + 1;
            }
            if (count <= n) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }


}
