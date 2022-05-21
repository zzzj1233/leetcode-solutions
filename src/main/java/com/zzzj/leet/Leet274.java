package com.zzzj.leet;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-10 19:02
 */
public class Leet274 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            final int[] arr = ArrayUtil.generateArray(10, 0, 1000);
            final int[] origin = Arrays.copyOfRange(arr, 0, arr.length);
            if (hIndex(arr) != right(arr)) {
                System.out.println(Arrays.toString(origin));
                return;
            }
        }
    }

    public static int hIndex(int[] citations) {
        int[] bucket = new int[1001];

        for (int citation : citations) {
            bucket[citation]++;
        }


        int pre = 0;

        for (int i = bucket.length - 1; i > 0; i--) {
            if (bucket[i] + pre >= i) {
                return i;
            }
            pre += bucket[i];
        }

        return 0;
    }


    public static int right(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

}
